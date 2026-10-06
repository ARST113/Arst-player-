package com.brouken.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * In-memory HTTP cookies for one playback/API session.
 *
 * Just+ Player 2.1.2 keeps cookies received from a server for the rest of the session. An explicit
 * Cookie header supplied by the launcher wins over the stored jar for that request.
 */
final class SessionCookieInterceptor implements Interceptor {

    private final List<Cookie> cookies = new ArrayList<>();

    synchronized void clear() {
        cookies.clear();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();
        final long now = System.currentTimeMillis();

        final Request.Builder request = original.newBuilder();
        if (original.header("Cookie") == null) {
            final String cookieHeader = cookieHeader(original.url(), now);
            if (cookieHeader != null) {
                request.header("Cookie", cookieHeader);
            }
        }

        final Response response = chain.proceed(request.build());
        final List<Cookie> received = Cookie.parseAll(original.url(), response.headers());
        if (!received.isEmpty()) {
            save(received, now);
        }
        return response;
    }

    private synchronized String cookieHeader(okhttp3.HttpUrl url, long now) {
        purgeExpired(now);
        final StringBuilder out = new StringBuilder();
        for (Cookie cookie : cookies) {
            if (!cookie.matches(url)) {
                continue;
            }
            if (out.length() > 0) {
                out.append("; ");
            }
            out.append(cookie.name()).append('=').append(cookie.value());
        }
        return out.length() == 0 ? null : out.toString();
    }

    private synchronized void save(List<Cookie> incoming, long now) {
        purgeExpired(now);
        for (Cookie cookie : incoming) {
            for (Iterator<Cookie> iterator = cookies.iterator(); iterator.hasNext();) {
                final Cookie old = iterator.next();
                if (sameIdentity(old, cookie)) {
                    iterator.remove();
                }
            }
            if (cookie.expiresAt() > now) {
                cookies.add(cookie);
            }
        }
    }

    private void purgeExpired(long now) {
        for (Iterator<Cookie> iterator = cookies.iterator(); iterator.hasNext();) {
            if (iterator.next().expiresAt() <= now) {
                iterator.remove();
            }
        }
    }

    private static boolean sameIdentity(Cookie a, Cookie b) {
        return a.name().equals(b.name())
                && a.domain().equals(b.domain())
                && a.path().equals(b.path());
    }
}

package com.brouken.player;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Vendor 2.4.9 behavior: on HTTP 429 wait briefly, retry the media GET/HEAD once,
 * and propagate a second 429 to the normal player error path.
 *
 * Never retry non-idempotent requests, never consume a response body without closing it,
 * and never recursively issue multiple retries during a rate-limiting response.
 */
final class ArxHttp429RetryInterceptor implements Interceptor {
    static final long DEFAULT_DELAY_MS = 1000L;
    static final long MAX_DELAY_MS = 5000L;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() != 429 || !canRetry(request.method())) {
            return response;
        }

        final long delay = retryDelayMs(response.header("Retry-After"));
        response.close();
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IOException("Interrupted while waiting to retry HTTP 429", exception);
        }
        // Exactly one additional request. Another HTTP 429 is returned unchanged.
        return chain.proceed(request);
    }

    static boolean canRetry(String method) {
        return "GET".equalsIgnoreCase(method) || "HEAD".equalsIgnoreCase(method);
    }

    static long retryDelayMs(String header) {
        if (header == null) return DEFAULT_DELAY_MS;
        try {
            long seconds = Long.parseLong(header.trim());
            if (seconds < 0) return DEFAULT_DELAY_MS;
            return seconds >= MAX_DELAY_MS / 1000L ? MAX_DELAY_MS : seconds * 1000L;
        } catch (NumberFormatException e) {
            // HTTP dates are legal Retry-After values too; use a safe bounded fallback.
            return DEFAULT_DELAY_MS;
        }
    }
}

package com.brouken.player;

import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Configures platform MediaDrm for DASH/HLS and externally launched links.
 * Licence acquisition and decryption remain in Media3/Android MediaDrm.
 * Do not embed, log or store licence responses or content keys.
 */
final class ArxDrm {
    private ArxDrm() {}

    static String param(Uri uri, String key) {
        if (uri == null || !uri.isHierarchical()) return null;
        try { return uri.getQueryParameter(key); }
        catch (UnsupportedOperationException | IllegalArgumentException ignored) { return null; }
    }

    static String text(Bundle bundle, String... keys) {
        if (bundle == null) return null;
        for (String key : keys) {
            Object value = bundle.get(key);
            if (value instanceof String && !((String) value).trim().isEmpty())
                return ((String)value).trim();
        }
        return null;
    }

    static boolean configure(MediaItem.Builder item, Uri uri, Bundle extras) {
        Bundle drm = extras == null ? null : extras.getBundle("drm");
        String scheme = text(drm, "scheme", "type", "system");
        if (scheme == null) scheme = text(extras, "drm_scheme", "drm_type", "drm");
        if (scheme == null) scheme = param(uri, "drm");
        String license = text(drm, "license_url", "license_uri", "license");
        if (license == null) license = text(extras, "drm_license_url", "drm_license", "license_url");
        if (license == null) license = param(uri, "drm_license_url");
        if (license == null) license = param(uri, "license_url");
        if (license == null) license = param(uri, "license");
        if (scheme == null && license == null) return false;
        if (scheme == null) scheme = "widevine";
        UUID uuid;
        switch (scheme.toLowerCase(Locale.ROOT)) {
            case "widevine":
            case "com.widevine.alpha":
                uuid = C.WIDEVINE_UUID; break;
            case "clearkey":
            case "clear_key":
            case "org.w3.clearkey":
                uuid = C.CLEARKEY_UUID; break;
            default:
                Utils.log("drm: unsupported scheme " + scheme);
                return false;
        }
        MediaItem.DrmConfiguration.Builder config = new MediaItem.DrmConfiguration.Builder(uuid);
        if (license != null) config.setLicenseUri(license);
        Bundle headerBundle = drm == null ? null : drm.getBundle("headers");
        if (headerBundle != null) {
            Map<String,String> headers = new HashMap<>();
            for (String key : headerBundle.keySet()) {
                Object value = headerBundle.get(key);
                if (value instanceof String) headers.put(key,(String)value);
            }
            config.setLicenseRequestHeaders(headers);
        }
        item.setDrmConfiguration(config.build());
        Utils.log("drm: configured " + scheme + (license == null ? " (manifest licence)" : " (external licence)"));
        return true;
    }

    static boolean isProtected(MediaItem item) {
        return item != null && item.localConfiguration != null &&
                item.localConfiguration.drmConfiguration != null;
    }
}

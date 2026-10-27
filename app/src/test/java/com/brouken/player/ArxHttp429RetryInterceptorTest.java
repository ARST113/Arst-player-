package com.brouken.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArxHttp429RetryInterceptorTest {
    @Test
    public void retriesOnlyIdempotentMediaMethods() {
        assertTrue(ArxHttp429RetryInterceptor.canRetry("GET"));
        assertTrue(ArxHttp429RetryInterceptor.canRetry("HEAD"));
        assertFalse(ArxHttp429RetryInterceptor.canRetry("POST"));
        assertFalse(ArxHttp429RetryInterceptor.canRetry("PATCH"));
    }

    @Test
    public void serverRetryAfterCannotCauseUnboundedWait() {
        assertEquals(1000L, ArxHttp429RetryInterceptor.retryDelayMs(null));
        assertEquals(1000L, ArxHttp429RetryInterceptor.retryDelayMs("garbage"));
        assertEquals(1000L, ArxHttp429RetryInterceptor.retryDelayMs("-1"));
        assertEquals(2000L, ArxHttp429RetryInterceptor.retryDelayMs("2"));
        assertEquals(5000L, ArxHttp429RetryInterceptor.retryDelayMs("120"));
        assertEquals(5000L, ArxHttp429RetryInterceptor.retryDelayMs("9223372036854775807"));
    }
}

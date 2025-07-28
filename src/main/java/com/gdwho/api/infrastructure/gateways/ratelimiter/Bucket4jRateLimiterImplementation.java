package com.gdwho.api.infrastructure.gateways.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.gdwho.api.application.gateways.RateLimiterGateway;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import java.time.Duration;

@Component
public class Bucket4jRateLimiterImplementation implements RateLimiterGateway {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private static final int TIME_LIMIT = 1;
    private static final int TOKENS_LIMIT = 10;
    private static final int AUTH_LIMIT = 3;

    private Bucket createBucket(String route) {
        Bandwidth limit;

        switch (route) {
            case "/auth":
                limit = Bandwidth.classic(AUTH_LIMIT, Refill.greedy(TOKENS_LIMIT, Duration.ofMinutes(TIME_LIMIT)));
                break;
            default:
                limit = Bandwidth.classic(20, Refill.greedy(TOKENS_LIMIT, Duration.ofMinutes(TIME_LIMIT)));
        }

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    public boolean tryConsume(String key) {
        String[] parts = key.split("\\|", 2);

        if (parts.length < 2)
            return false;

        String route = parts[1];
        Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket(route));

        return bucket.tryConsume(1);
    }
}

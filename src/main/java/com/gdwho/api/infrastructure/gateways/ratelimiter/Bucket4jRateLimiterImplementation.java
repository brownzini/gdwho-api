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

    private static final int TIME_LIMIT = 12;
    private static final int TOKENS_LIMIT = 10;
    private static final int DEFAULT_LIMIT = 5;

    private static final int AUTH_LOGIN_LIMIT = 5;
    private static final int AUTH_REGISTER_LIMIT = 3;

    private static final int GAME_CREATE_LIMIT = 2;

    private Bucket createBucket(String route) {
        Bandwidth limit;

        switch (route) {
            case "/v1/api/auth/login":
                limit = Bandwidth.classic(AUTH_LOGIN_LIMIT,
                        Refill.greedy(TOKENS_LIMIT, Duration.ofMinutes(TIME_LIMIT)));
                break;
            case "/v1/api/auth/register":
                limit = Bandwidth.classic(AUTH_REGISTER_LIMIT,
                        Refill.greedy(TOKENS_LIMIT, Duration.ofMinutes(TIME_LIMIT)));
                break;
            case "/v1/api/game/create":
                limit = Bandwidth.classic(GAME_CREATE_LIMIT,
                        Refill.greedy(TOKENS_LIMIT, Duration.ofMinutes(TIME_LIMIT)));
                break;
            default:
                limit = Bandwidth.classic(DEFAULT_LIMIT, Refill.greedy(TOKENS_LIMIT, Duration.ofMinutes(TIME_LIMIT)));
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

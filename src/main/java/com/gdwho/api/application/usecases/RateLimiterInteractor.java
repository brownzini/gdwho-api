package com.gdwho.api.application.usecases;

import com.gdwho.api.application.gateways.RateLimiterGateway;

public class RateLimiterInteractor {
    private final RateLimiterGateway rateLimiterGateway;

    public RateLimiterInteractor(RateLimiterGateway rateLimiterGateway) {
        this.rateLimiterGateway = rateLimiterGateway;
    }

    public boolean tryConsume(String key) {
        return rateLimiterGateway.tryConsume(key);
    }
}

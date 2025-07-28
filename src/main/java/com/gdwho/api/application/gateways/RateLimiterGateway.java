package com.gdwho.api.application.gateways;

public interface RateLimiterGateway {
      boolean tryConsume(String key);
}

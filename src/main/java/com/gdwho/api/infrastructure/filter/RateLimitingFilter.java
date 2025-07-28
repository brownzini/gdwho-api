package com.gdwho.api.infrastructure.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gdwho.api.application.gateways.RateLimiterGateway;

import java.io.IOException;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final RateLimiterGateway rateLimiterGateway;

    public RateLimitingFilter(RateLimiterGateway rateLimiterGateway) {
        this.rateLimiterGateway = rateLimiterGateway;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String ip = request.getRemoteAddr();
        String path = request.getRequestURI();
        String key = ip + "|" + path;

        if (rateLimiterGateway.tryConsume(key)) {
            filterChain.doFilter(request, response);
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

            String json = """
                    {
                      "error": "Too Many Requests",
                      "message": "You have exceeded the request limit. Try again soon."
                    }
                    """;

            response.getWriter().write(json);
        }
    }
}

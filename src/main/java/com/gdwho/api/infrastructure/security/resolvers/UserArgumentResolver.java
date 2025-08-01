package com.gdwho.api.infrastructure.security.resolvers;

import org.springframework.core.MethodParameter;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.infrastructure.gateways.exceptions.OperationFailedException;
import com.gdwho.api.infrastructure.security.exceptions.UnauthorizedException;
import com.gdwho.api.infrastructure.security.jwt.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authenticated.class)
                && parameter.getParameterType().equals(AuthenticatedUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Long userId = JwtUtil.extractUserId(token);
            String roleStr = JwtUtil.extractUserRole(token);
            try {
                RoleEnum role = RoleEnum.valueOf(roleStr);
                return new AuthenticatedUser(userId, role);
            } catch (IllegalArgumentException e) {
                throw new OperationFailedException("[Role Error]: Operation not permitted for this role ");
            }
        }

        throw new UnauthorizedException("[JWT Error]: Missing or invalid JWT token");
    }
}
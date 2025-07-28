package com.gdwho.api.domain.entities.user;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private final String role;

    private static final Map<String, RoleEnum> LOOKUP;

    static {
        LOOKUP = Arrays.stream(RoleEnum.values())
                .collect(Collectors.toMap(
                    e -> e.name().toLowerCase(),
                    e -> e
                ));
    }

    RoleEnum(String role) {
        this.role = role;
    }

    @JsonCreator
    public static RoleEnum fromString(String text) {
        if (text == null) return USER;
        return LOOKUP.getOrDefault(text.toLowerCase(), USER);
    }

    @Override
    public String toString() {
        return role;
    }
}
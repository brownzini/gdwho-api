package com.gdwho.api.infrastructure.persistence.specification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.infrastructure.persistence.dtos.user.UserFilterDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

import jakarta.persistence.criteria.Predicate;

public class UserSpecifications {

    public static Specification<UserDBEntity> withFilters(UserFilterDTO filter) {
        return (root, query, cb) -> {
            final int expectedPredicates = 3;
            List<Predicate> predicates = new ArrayList<>(expectedPredicates);

            String username = filter.username();
            if (username != null) {
                String likeUsernameField = "%" + username.toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("username")), likeUsernameField));
            }

            if (filter.role() != null) {
                String role = filter.role().toString();
                RoleEnum roleEnum = RoleEnum.valueOf(role.toUpperCase()); 
                predicates.add(cb.equal(root.get("role"), roleEnum));
            }

            Instant createdAfter = filter.createdAfter();
            if (createdAfter != null) {
                predicates.add(cb.greaterThan(root.get("createdAt"), createdAfter));
            }

            return predicates.isEmpty()
                    ? cb.conjunction()
                    : cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}

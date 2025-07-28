package com.gdwho.api.infrastructure.security.implemention;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserUseCase userUseCase;

    public UserDetailsServiceImpl(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDomainEntity user = userUseCase.findByUsername(username);

        return User.builder()
                .username(user.username())
                .password(user.password())
                .roles(user.role().name())
                .build();
    }
}
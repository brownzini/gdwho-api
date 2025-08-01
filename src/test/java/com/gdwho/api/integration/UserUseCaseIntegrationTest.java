package com.gdwho.api.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.EntriesDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserUseCaseIntegrationTest {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should return list of users from database using real gateway")
    void shouldReturnListOfUsers() {

        userRepository.deleteAll();

        UserDBEntity user1 = new UserDBEntity(
                "user", "password123",
                RoleEnum.USER, null,
                new ArrayList<>(), new ArrayList<>());

        DataDBEntity data1 = new DataDBEntity("value test");
        data1.setUser(user1);
        user1.getData().add(data1);

        EntriesDBEntity entry1 = new EntriesDBEntity("input test", "output test", 1.0);
        entry1.setUser(user1);
        user1.getEntries().add(entry1);

        UserDBEntity user2 = new UserDBEntity(
                "admin", "adminpass123",
                RoleEnum.USER, null,
                new ArrayList<>(), new ArrayList<>());

        DataDBEntity data2 = new DataDBEntity("value test");
        data2.setUser(user2);
        user2.getData().add(data2);

        EntriesDBEntity entry2 = new EntriesDBEntity("input test", "output test", 1.0);
        entry2.setUser(user2);
        user2.getEntries().add(entry2);

        userRepository.saveAll(List.of(user1, user2));

        var result = userUseCase.getAllUsers(Pageable.unpaged(),
                new com.gdwho.api.domain.entities.filter.UserFilterDomain(null, null));

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(u -> u.username().equals("user")));
        assertTrue(result.stream().anyMatch(u -> u.username().equals("admin")));
    }

    @Test
    @DisplayName("Should throw exception when username not found")
    void shouldThrowWhenUsernameNotFound() {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userUseCase.findByUsername("inexistente");
        });

        assertTrue(exception.getMessage().toLowerCase().contains("not found"));
    }
}

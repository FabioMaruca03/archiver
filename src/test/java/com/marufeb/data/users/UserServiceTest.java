package com.marufeb.data.users;

import com.marufeb.data.TestDataApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestDataApplication.class)
class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    void save() {
        final var user = new User("test@xyz.com");

        final var savedUser = service.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getEmail());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    void findById() {
        final var user = new User("test@xyz.com");

        final var savedUser = service.save(user);

        service.findByEmail(savedUser.getEmail()).ifPresentOrElse(
                u -> {
                    assertNotNull(u);
                    assertNotNull(u.getEmail());
                    assertEquals(user.getEmail(), u.getEmail());
                },
                () -> fail("User not found")
        );
    }

    @Test
    void findAll() {
        final var user1 = new User("test@xyz.com");
        final var user2 = new User("test2@xyz.com");

        service.save(user1);
        service.save(user2);

        service.findAll().forEach(
                u -> {
                    assertNotNull(u);
                    assertNotNull(u.getEmail());
                    assertTrue(u.getEmail().equals(user1.getEmail()) || u.getEmail().equals(user2.getEmail()));
                }
        );
    }

    @Test
    void deleteById() {
        final var user = new User("test@xyz.com");

        final var savedUser = service.save(user);

        service.deleteByEmail(savedUser.getEmail());

        service.findByEmail(savedUser.getEmail())
                .ifPresent(u -> fail("User not deleted"));
    }
}
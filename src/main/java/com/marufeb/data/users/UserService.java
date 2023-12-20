package com.marufeb.data.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User document) {
        return repository.save(document);
    }

    public Optional<User> findByEmail(String id) {
        return repository.findByEmail(id);
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }

    public void deleteByEmail(String email) {
        repository.deleteById(email);
    }

}

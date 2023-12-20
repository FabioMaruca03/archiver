package com.marufeb.data.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private String email;
    private LocalDateTime lastLogin;

    public User(String email) {
        this.email = email;
    }
}

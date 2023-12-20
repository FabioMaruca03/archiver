package com.marufeb.data.commons;

import com.marufeb.data.users.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class StorableEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User owner;
    private String password;

    private byte[] data;
    private String name;
    private String description;
    @Transient
    private String storage;
    private String extension;

    @Builder.Default
    private boolean deleted = false;
    @Builder.Default
    private boolean archived = false;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private LocalDateTime lastAccess;
    private LocalDateTime deletionDate;
    private LocalDateTime archiveDate;

    @PrePersist
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
        lastAccess = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    @PostLoad
    public void postLoad() {
        extension = name.substring(name.lastIndexOf("."));
    }

}

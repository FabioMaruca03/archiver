package com.marufeb.data.documents;

import com.marufeb.data.commons.StorableEntity;
import com.marufeb.data.users.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "documents")
@EqualsAndHashCode(callSuper = true)
public class Document extends StorableEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @OneToMany
    private List<User> sharedWith;

}

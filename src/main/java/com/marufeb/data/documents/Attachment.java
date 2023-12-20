package com.marufeb.data.documents;

import com.marufeb.data.commons.StorableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "attachments")
@EqualsAndHashCode(callSuper = true)
public class Attachment extends StorableEntity {

    @JoinColumn(name = "document_id", table = "documents")
    private UUID document;

}

package com.marufeb.data.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface DocumentRepository extends JpaRepository<Document, UUID> {
}

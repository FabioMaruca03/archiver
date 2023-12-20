package com.marufeb.data.documents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public Optional<Document> findById(UUID id) {
        return documentRepository.findById(id);
    }

    public Iterable<Document> findAll() {
        return documentRepository.findAll();
    }

    public void deleteById(UUID id) {
        documentRepository.deleteById(id);
    }

}

package com.marufeb.data.documents.api;

import com.marufeb.data.documents.Document;
import com.marufeb.data.documents.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/documents")
class DocumentsController {
    private final DocumentService documentService;

    @PostMapping
    public Document save(Document document) {
        return documentService.save(document);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> findById(@PathVariable UUID id) {
        return ResponseEntity.of(documentService.findById(id));
    }

    @GetMapping("/all")
    public Iterable<Document> findAll() {
        return documentService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        documentService.deleteById(id);
    }
}

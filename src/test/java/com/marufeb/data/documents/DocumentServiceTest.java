package com.marufeb.data.documents;

import com.marufeb.data.TestDataApplication;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestDataApplication.class)
class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Test
    void save() {
        final var document = Document.builder()
                .name("Test")
                .description("Test")
                .attachments(List.of(Attachment.builder()
                        .name("Test")
                        .description("Test")
                        .build())
                )
                .build();

        final var savedDocument = documentService.save(document);

        assertNotNull(savedDocument);
        assertNotNull(savedDocument.getId());
        assertEquals("Test", savedDocument.getName());
        assertEquals("Test", savedDocument.getDescription());
        assertEquals(1, savedDocument.getAttachments().size());
    }

    @Test
    @Transactional
    void findById() {
        final var document = Document.builder()
                .name("Test")
                .description("Test")
                .attachments(List.of(Attachment.builder()
                        .name("Test")
                        .description("Test")
                        .build())
                )
                .build();

        final var savedDocument = documentService.save(document);

        documentService.findById(savedDocument.getId()).ifPresentOrElse(
                d -> {
                    assertNotNull(d);
                    assertNotNull(d.getId());
                    assertEquals("Test", d.getName());
                    assertEquals("Test", d.getDescription());
                    assertEquals(1, d.getAttachments().size());
                },
                () -> fail("Document not found")
        );
    }

    @Test
    @Transactional
    void findAll() {
        final var document1 = Document.builder()
                .name("Test1")
                .description("Test")
                .attachments(List.of(Attachment.builder()
                        .name("Test")
                        .description("Test")
                        .build())
                )
                .build();

        final var document2 = Document.builder()
                .name("Test2")
                .description("Test")
                .attachments(List.of(Attachment.builder()
                        .name("Test")
                        .description("Test")
                        .build())
                )
                .build();

        documentService.save(document1);
        documentService.save(document2);

        final var documents = documentService.findAll();
        documents.forEach(d -> {
            assertNotNull(d);
            assertNotNull(d.getId());
            assertEquals("Test", d.getDescription());
            assertEquals(1, d.getAttachments().size());
        });
    }

    @Test
    void deleteById() {
        final var document = Document.builder()
                .name("Test")
                .description("Test")
                .attachments(List.of(Attachment.builder()
                        .name("Test")
                        .description("Test")
                        .build())
                )
                .build();

        final var savedDocument = documentService.save(document);

        documentService.deleteById(savedDocument.getId());

        documentService.findById(savedDocument.getId()).ifPresentOrElse(
                d -> fail("Document not deleted"),
                () -> assertTrue(true)
        );
    }
}
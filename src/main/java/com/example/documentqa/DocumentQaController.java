package com.example.documentqa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentQaController {
    private final DocumentQaService service;

    public DocumentQaController(DocumentQaService service) {
        this.service = service;
    }

    @GetMapping("/documents")
    public List<DocumentQaService.DocumentSummary> documents() {
        return service.listDocuments();
    }

    @PostMapping("/documents")
    public DocumentQaService.DocumentSummary addDocument(@RequestBody AddDocumentRequest request) {
        if (request.name() == null || request.name().isBlank() || request.content() == null || request.content().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Document name and content are required");
        }
        return service.addDocument(request.name(), request.content());
    }

    @PostMapping("/ask")
    public DocumentQaService.AnswerResponse ask(@RequestBody AskRequest request) {
        if (request.question() == null || request.question().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter a question");
        }
        return service.answer(request.question());
    }

    public record AddDocumentRequest(String name, String content) {}
    public record AskRequest(String question) {}
}

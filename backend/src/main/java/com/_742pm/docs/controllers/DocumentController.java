package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/document")
    public Document getDocument(@RequestBody UUID id) {
        var document = documentService.getById(id);
        return document.orElse(null);
    }

    @DeleteMapping("/document")
    public void deleteDocument(@RequestBody UUID id) {
        documentService.delete(id);
    }

    @PutMapping("/document")
    public void putDocument(@RequestBody Document document, @RequestBody UUID id) {
        documentService.update(id, document);
    }
}

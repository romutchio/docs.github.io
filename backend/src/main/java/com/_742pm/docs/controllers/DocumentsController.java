package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentsController {

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/documents")
    public List<Document> getDocuments(@RequestBody User user) {
        return documentService.findAll(user);
    }

    @PostMapping("/documents")
    public void postDocument(@RequestBody Document document) {
        documentService.create(document);
    }
}


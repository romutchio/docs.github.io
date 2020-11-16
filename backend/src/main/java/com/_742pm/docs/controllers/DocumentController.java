package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DocumentController
{

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/document")
    public Document getDocument(@RequestBody UUID id)
    {
        return documentService.getById(id).orElse(null);
    }

    @PostMapping("/document")
    public UUID postDocument(@RequestBody Document document)
    {
        return documentService.create(document).getId();
    }

    @PutMapping("/document/{id}")
    public void updateDocument(@RequestBody Document document, @PathVariable("id") UUID id)
    {
        documentService.update(id, document);
    }

    @DeleteMapping("/documents/{id}")
    public void deleteDocument(@PathVariable("id") UUID id)
    {
        documentService.delete(id);
    }

}

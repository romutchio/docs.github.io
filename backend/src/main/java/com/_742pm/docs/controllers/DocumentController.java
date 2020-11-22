package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class DocumentController
{

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/document/{id}")
    public Document getDocument(@PathVariable("id") UUID id)
    {
        return documentService.getById(id).orElse(null);
    }

    @PostMapping(value = "/document", consumes = "application/json")
    public UUID postDocument(@RequestBody Document document, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        document.setUserId(user.getId());
        return documentService.create(document).getId();

    }

    @PutMapping(value = "/document/{id}", consumes = "application/json")
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

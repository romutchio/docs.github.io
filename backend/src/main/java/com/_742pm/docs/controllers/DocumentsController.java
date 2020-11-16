package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.service.IDocumentService;
import com._742pm.docs.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class DocumentsController
{

    @Autowired
    private IDocumentService documentService;

    @GetMapping(value = "/documents/query", params = "query")
    public List<Document> getDocumentsByQuery(@RequestParam(name = "query") String query, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return documentService.findByQuery(query, user);
    }

    @GetMapping(value = "/documents/tag", params = "tag")
    public List<Document> getDocumentsByTag(@RequestParam(name = "tag") String tag, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return documentService.findByTag(tag, user);
    }

    @GetMapping(value = "/documents")
    public List<Document> getAllDocuments(@AuthenticationPrincipal OAuth2User principal)
    {

        var user = User.fromPrincipal(principal);
        return documentService.findAll(user);
    }


    @PostMapping("/documents")
    public UUID postDocument(@RequestBody Document document)
    {
        return documentService.create(document).getId();
    }

    @PutMapping("/documents/{id}")
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


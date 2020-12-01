package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}


package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.service.IDocumentService;
import com._742pm.docs.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class DocumentsController {

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private ITagService tagService;

    @GetMapping("/documents")
    public List<Document> getDocuments(@RequestParam(name = "query") String query, @RequestBody User user) {
        if (!query.equals("")) {
            if (query.startsWith("#")){
                var documentIds = tagService.findDocumentsByTag(query, user);
                return documentIds
                        .stream()
                        .map(x -> documentService.getById(x).orElse(null))
                        .collect(Collectors.toList());
            }
            else {
                return documentService.findByQuery(query, user);
            }
        }
        return documentService.findAll(user);
    }


    @PostMapping("/documents")
    public UUID postDocument(@RequestBody Document document) {

        return documentService.create(document).getId();
    }
}


package com._742pm.docs.service;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IDocumentService {
    Optional<Document> getById(UUID id);
    void delete(UUID id);
    void update(UUID documentId, Document document);
    List<Document> findAll(User user);
    void create(Document document);
}

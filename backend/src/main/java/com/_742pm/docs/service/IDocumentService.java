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
    List<Document> findAll(User user);
    List<Document> findByQuery(String query, User user);
    void create(Document document);
    void update(UUID documentId, Document document);
    void delete(UUID id);
}

package com._742pm.docs.service;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService implements IDocumentService{

    @Autowired
    private DocumentRepository repository;

    @Override
    public Optional<Document> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void update(UUID documentId, Document document) {
        var doc = repository.findById(documentId).orElse(null);
        if (doc != null) {
            if (!document.getName().equals(doc.getName()))
                doc.setName(document.getName());
            repository.save(doc);
        }
    }

    @Override
    public List<Document> findAll(User user) {
        return repository.findDocumentsByUserId(user.getId());
    }

    @Override
    public List<Document> findByQuery(String query, User user) {
        var documents = repository.findDocumentsByNameContains(query);
        return documents.stream().filter(x -> x.getUserId().equals(user.getId())).collect(Collectors.toList());
    }

    @Override
    public void create(Document document) {

        repository.save(document);
    }
}

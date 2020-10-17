package com._742pm.docs.repository;

import com._742pm.docs.models.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends CrudRepository<Document, UUID> {
    List<Document> findDocumentsByUserId(UUID user_id);
    List<Document> findDocumentsByNameContains(String query);
}


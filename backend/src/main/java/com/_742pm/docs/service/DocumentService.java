package com._742pm.docs.service;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService implements IDocumentService
{

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private ITagService tagService;


    @Override
    public Optional<Document> getById(UUID id)
    {
        return repository.findById(id);
    }

    @Override
    public void delete(UUID id)
    {
        repository.deleteById(id);
    }

    @Override
    public void update(UUID documentId, Document document)
    {
        var doc = repository.findById(documentId).orElse(null);
        if (doc != null)
        {
            doc.setName(document.getName());
            doc.setData(document.getData());
            repository.save(doc);
        }
    }

    @Override
    public List<Document> findAll(User user)
    {
        return repository.findDocumentsByUserId(user.getId());
    }

    @Override
    public List<Document> findByQuery(String query, User user)
    {
        var documents = repository.findDocumentsByNameContains(query);
        return documents.stream().filter(x -> x.getUserId().equals(user.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Document> findByTag(String tag, User user)
    {
        return tagService.findDocumentsByTag(tag, user)
                         .stream()
                         .map(x -> getById(x).orElse(null))
                         .collect(Collectors.toList());
    }

    @Override
    public List<Document> findByTags(String[] tags, User user)
    {
        return Arrays.stream(tags).flatMap(x -> findByTag(x, user).stream()).distinct().collect(Collectors.toList());
    }

    @Override
    public Document create(Document document)
    {

        return repository.save(document);
    }
}

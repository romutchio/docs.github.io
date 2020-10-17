package com._742pm.docs.service;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import com._742pm.docs.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagService implements ITagService{

    @Autowired
    private TagRepository repository;

    @Override
    public List<Tag> findAll(User user) {
        return repository.findTagsByUserId(user.getId());
    }

    @Override
    public List<UUID> findDocumentsByTag(String tag, User user) {
        var tags = repository.findTagsByName(tag);
        return tags
                .stream()
                .filter(x -> x.getUserId().equals(user.getId()))
                .map(Tag::getDocumentId)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Tag tag) {
        repository.save(tag);
    }
}

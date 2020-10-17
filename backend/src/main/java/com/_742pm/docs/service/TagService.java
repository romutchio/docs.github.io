package com._742pm.docs.service;

import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import com._742pm.docs.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements ITagService{

    @Autowired
    private TagRepository repository;

    @Override
    public List<Tag> findAll(User user) {
        return repository.findTagsByUserId(user.getId());
    }

    @Override
    public void create(Tag tag) {
        repository.save(tag);
    }
}

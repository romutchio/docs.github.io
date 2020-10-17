package com._742pm.docs.service;

import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITagService {
    List<Tag> findAll(User user);
    void create(Tag tag);
}

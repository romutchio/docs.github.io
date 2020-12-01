package com._742pm.docs.service;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ITagService {
    List<Tag> findAll(User user);
    List<Tag> getTags(User user, Document document);
    List<UUID> findDocumentsByTag(String tag, User user);
    UUID create(Tag tag);
}

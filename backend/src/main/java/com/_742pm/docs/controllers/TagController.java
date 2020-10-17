package com._742pm.docs.controllers;

import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import com._742pm.docs.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    private ITagService tagService;

    @GetMapping("/tags")
    public List<Tag> getDocuments(@RequestBody User user) {
        return tagService.findAll(user);
    }

    @PostMapping("/tags")
    public void postDocuments(@RequestBody Tag tag) {
        tagService.create(tag);
    }

}

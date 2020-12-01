package com._742pm.docs.controllers;

import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import com._742pm.docs.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    private ITagService tagService;

    @GetMapping("/tags")
    public List<Tag> getDocuments(@AuthenticationPrincipal OAuth2User principal ) {
        var user = User.fromPrincipal(principal);
        return tagService.findAll(user);
    }

    @PostMapping("/tags")
    public void postDocuments(@RequestBody Tag tag) {
        tagService.create(tag);
    }

}

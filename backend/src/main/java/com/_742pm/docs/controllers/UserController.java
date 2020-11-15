package com._742pm.docs.controllers;

import com._742pm.docs.models.User;
import com._742pm.docs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public List<User> findUsers() {
        return userService.findAll();
    }

    @GetMapping("/users")
    public User authenticate() {
        return userService.findAll().get(0);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

}

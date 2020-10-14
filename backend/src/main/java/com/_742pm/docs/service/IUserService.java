package com._742pm.docs.service;

import com._742pm.docs.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<User> findAll();
    void create(User user);
}

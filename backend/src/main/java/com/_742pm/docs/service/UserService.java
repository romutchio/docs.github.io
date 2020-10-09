package com._742pm.docs.service;

import com._742pm.docs.models.User;
import com._742pm.docs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }
}

package com.example.soswedding.service;

import com.example.soswedding.model.User;

public interface UserService {
    public User getByUuid(String uuid);
    public User getByEmail(String email);
    public User updateUser(User user);
}

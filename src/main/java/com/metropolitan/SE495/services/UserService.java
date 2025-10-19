package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    Optional<?> getById(Long id);

    User add(User address);

    User update(User address);

    void delete(Long id);
}

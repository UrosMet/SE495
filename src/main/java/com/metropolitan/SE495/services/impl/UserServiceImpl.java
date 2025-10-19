package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.User;
import com.metropolitan.SE495.repository.UserRepository;
import com.metropolitan.SE495.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User add(User address) {
        return userRepository.save(address);
    }

    @Override
    public User update(User address) {
        return userRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

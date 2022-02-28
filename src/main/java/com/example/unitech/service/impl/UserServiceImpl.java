package com.example.unitech.service.impl;

import com.example.unitech.entity.User;
import com.example.unitech.exception.UserNotFoundException;
import com.example.unitech.repository.UserRepository;
import com.example.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        log.info("Create user: {}", user);
        userRepository.save(user);
    }

    @Override
    public User getUserByPin(String pin) {
        log.info("Get user by pin: {}", pin);
        return userRepository.findByPin(pin)
                .orElseThrow(() -> new UserNotFoundException("User not found with pin: " + pin));
    }

    @Override
    public boolean existsUserByPin(String pin) {
        log.info("Check user exists by pin: {}", pin);
        return userRepository.existsUserByPin(pin);
    }
}

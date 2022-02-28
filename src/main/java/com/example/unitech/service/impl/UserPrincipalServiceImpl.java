package com.example.unitech.service.impl;

import com.example.unitech.entity.User;
import com.example.unitech.service.UserPrincipalService;
import com.example.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPrincipalServiceImpl implements UserPrincipalService {

    private final UserService userService;

    public User getCurrentUser() {
        log.info("Get current user");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByPin(authentication.getName());
    }
}

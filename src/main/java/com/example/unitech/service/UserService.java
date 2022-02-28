package com.example.unitech.service;

import com.example.unitech.entity.User;

public interface UserService {

    void create(User user);

    User getUserByPin(String pin);

    boolean existsUserByPin(String pin);

}

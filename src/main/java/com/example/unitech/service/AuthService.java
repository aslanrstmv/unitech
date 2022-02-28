package com.example.unitech.service;

import com.example.unitech.dto.request.SignUpRequestDto;
import com.example.unitech.dto.request.SignInRequestDto;

public interface AuthService {

    void signUp(SignUpRequestDto signUpRequestDto) throws Exception;

    String signIn(SignInRequestDto signInRequestDto);


}

package com.example.unitech.service.impl;

import com.example.unitech.dto.request.SignInRequestDto;
import com.example.unitech.dto.request.SignUpRequestDto;
import com.example.unitech.entity.User;
import com.example.unitech.enums.Role;
import com.example.unitech.security.JwtUtil;
import com.example.unitech.service.AuthService;
import com.example.unitech.service.CustomUserDetailsService;
import com.example.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        log.info("Sign up by pin: {}", signUpRequestDto.getPin());
        User user = new User();
        user.setPin(signUpRequestDto.getPin());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setRole(Role.USER);
        userService.create(user);
    }

    @Override
    public String signIn(SignInRequestDto signInRequestDto) {
        log.info("Sign in by pin: {}", signInRequestDto.getPin());
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(signInRequestDto.getPin(), signInRequestDto.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Incorrect pin or password", ex);
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(signInRequestDto.getPin());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return jwtToken;
    }


}

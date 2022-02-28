package com.example.unitech.controller;

import com.example.unitech.dto.request.SignUpRequestDto;
import com.example.unitech.dto.request.SignInRequestDto;
import com.example.unitech.dto.response.SignUpResponseDto;
import com.example.unitech.dto.response.SignInResponseDto;
import com.example.unitech.security.JwtUtil;
import com.example.unitech.service.AuthService;
import com.example.unitech.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService, CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
        authService.signUp(signUpRequestDto);
        return ResponseEntity.ok(new SignUpResponseDto("User created"));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        final String jwtToken = authService.signIn(signInRequestDto);
        return ResponseEntity.ok(new SignInResponseDto(jwtToken));
    }
}

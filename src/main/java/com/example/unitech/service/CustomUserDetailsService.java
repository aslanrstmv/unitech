package com.example.unitech.service;

import com.example.unitech.entity.User;
import com.example.unitech.exception.UserNotFoundException;
import com.example.unitech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        User user = userRepository.findByPin(pin)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with: %s ", pin)));
        return new org.springframework.security.core.userdetails.User(user.getPin(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }

}

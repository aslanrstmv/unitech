package com.example.unitech.repository;

import com.example.unitech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPin(String pin);

    boolean existsUserByPin(String pin);
}

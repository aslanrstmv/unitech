package com.example.unitech.repository;

import com.example.unitech.entity.Account;
import com.example.unitech.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(UUID accountNumber);

    List<Account> getAccountsByUserPinAndAccountStatus(String pin, AccountStatus accountStatus);

}

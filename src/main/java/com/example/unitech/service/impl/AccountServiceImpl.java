package com.example.unitech.service.impl;

import com.example.unitech.dto.request.CreateAccountRequestDto;
import com.example.unitech.dto.response.AccountResponseDto;
import com.example.unitech.entity.Account;
import com.example.unitech.entity.User;
import com.example.unitech.enums.AccountStatus;
import com.example.unitech.exception.AccountException;
import com.example.unitech.mapper.AccountMapper;
import com.example.unitech.repository.AccountRepository;
import com.example.unitech.service.AccountService;
import com.example.unitech.service.UserPrincipalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserPrincipalService userPrincipalService;

    private final AccountMapper accountMapper;

    @Override
    public void create(CreateAccountRequestDto createAccountRequestDto) {
        log.info("Creating an account: {}", createAccountRequestDto);
        User user = userPrincipalService.getCurrentUser();
        Account account = Account.builder()
                .name(createAccountRequestDto.getName())
                .accountNumber(UUID.randomUUID())
                .balance(new BigDecimal(100))
                .accountStatus(AccountStatus.ACTIVE)
                .user(user)
                .build();
        accountRepository.save(account);

    }

    @Override
    public List<AccountResponseDto> getActiveAccountsByUser() {
        log.info("getActiveAccountsByUser() is invoked");
        User user = userPrincipalService.getCurrentUser();
        return accountMapper.toDtos(accountRepository.getAccountsByUserPinAndAccountStatus(user.getPin(), AccountStatus.ACTIVE));
    }

    @Override
    public Account getAccountByAccountNumber(UUID accountNumber) {
        log.info("getAccountByAccountNumber() is invoked");
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountException(String.format("Account not found with: %s, ", accountNumber)));
    }

}

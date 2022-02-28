package com.example.unitech.service;

import com.example.unitech.dto.request.CreateAccountRequestDto;
import com.example.unitech.dto.response.AccountResponseDto;
import com.example.unitech.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    void create(CreateAccountRequestDto createAccountRequestDto);

//    List<AccountResponseDto> getActiveAccountsByUserPin(String pin);

    List<AccountResponseDto> getActiveAccountsByUser();

    Account getAccountByAccountNumber(UUID accountNumber);
}

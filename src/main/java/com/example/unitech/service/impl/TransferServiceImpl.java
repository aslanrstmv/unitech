package com.example.unitech.service.impl;

import com.example.unitech.dto.TransferPayload;
import com.example.unitech.entity.Account;
import com.example.unitech.enums.AccountStatus;
import com.example.unitech.exception.AccountException;
import com.example.unitech.repository.AccountRepository;
import com.example.unitech.service.AccountService;
import com.example.unitech.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void transfer(TransferPayload transferPayload) {

        Account accountFrom= accountService.getAccountByAccountNumber(transferPayload.getFrom());
        Account accountTo = accountService.getAccountByAccountNumber(transferPayload.getTo());

        if (accountFrom.equals(accountTo)) {
            throw new AccountException("You can't transfer money to the same account");
        }
        if (accountTo.getAccountStatus() == AccountStatus.INACTIVE) {
            throw new AccountException("You can't transfer money to the deactive account");
        }
        if (accountFrom.getBalance().compareTo(transferPayload.getAmount()) <= 0) {
            throw new AccountException("No enough money in balance");
        }
        accountFrom.setBalance((accountFrom.getBalance().subtract(transferPayload.getAmount())));
        accountRepository.save(accountFrom);
        accountTo.setBalance(accountTo.getBalance().add(transferPayload.getAmount()));
        accountRepository.save(accountTo);
    }
}

package com.example.unitech.controller;

import com.example.unitech.dto.TransferPayload;
import com.example.unitech.dto.request.CreateAccountRequestDto;
import com.example.unitech.dto.response.AccountResponseDto;
import com.example.unitech.dto.response.CreateAccountResponseDto;
import com.example.unitech.dto.response.TransferResponseDto;
import com.example.unitech.service.AccountService;
import com.example.unitech.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransferService transferService;

    public AccountController(AccountService accountService, TransferService transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @PostMapping()
    public ResponseEntity<CreateAccountResponseDto> create(@Valid @RequestBody CreateAccountRequestDto createAccountRequestDto) {
        accountService.create(createAccountRequestDto);
        return ResponseEntity.ok(new CreateAccountResponseDto("Account created"));
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponseDto>> getActiveAccounts() {
        return ResponseEntity.ok(accountService.getActiveAccountsByUser());
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDto> transfer(@Valid @RequestBody TransferPayload transferPayload) {
        transferService.transfer(transferPayload);
        return ResponseEntity.ok(new TransferResponseDto("Transfered successfully"));
    }


}

package com.example.unitech.dto.response;

import com.example.unitech.enums.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountResponseDto {
    private UUID accountNumber;
    private BigDecimal balance;
    private AccountStatus accountStatus;
}

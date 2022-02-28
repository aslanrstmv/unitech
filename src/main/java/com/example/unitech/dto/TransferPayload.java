package com.example.unitech.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferPayload {
    private UUID from;
    private UUID to;
    private BigDecimal amount;
}

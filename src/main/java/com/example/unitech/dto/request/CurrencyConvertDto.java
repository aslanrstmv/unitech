package com.example.unitech.dto.request;

import lombok.Data;

@Data
public class CurrencyConvertDto {
    private String baseCurrency;
    private String toCurrency;
}

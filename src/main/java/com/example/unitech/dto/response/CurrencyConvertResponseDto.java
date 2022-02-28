package com.example.unitech.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyConvertResponseDto {

    private String baseCurrency;
    private Map<String, Double> data;
}

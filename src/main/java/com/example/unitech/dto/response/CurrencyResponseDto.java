package com.example.unitech.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyResponseDto {
    private Map<String, String> query;
    private Map<String, Double> data;
}

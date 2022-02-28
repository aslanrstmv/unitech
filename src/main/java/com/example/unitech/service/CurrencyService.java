package com.example.unitech.service;

import com.example.unitech.dto.request.CurrencyConvertDto;
import com.example.unitech.dto.response.CurrencyConvertResponseDto;
import com.example.unitech.dto.response.CurrencyResponseDto;

public interface CurrencyService {

    CurrencyResponseDto getData(String baseCurrency);

    CurrencyConvertResponseDto convert(CurrencyConvertDto currencyConvertDto);
}

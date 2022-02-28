package com.example.unitech.service.impl;

import com.example.unitech.dto.request.CurrencyConvertDto;
import com.example.unitech.dto.response.CurrencyConvertResponseDto;
import com.example.unitech.dto.response.CurrencyResponseDto;
import com.example.unitech.service.CurrencyFeignClient;
import com.example.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyFeignClient currencyFeignClient;
    @Value("${CURRENCY_API_KEY}")
    private String API_KEY;

    @Override
    public CurrencyResponseDto getData(String baseCurrency) {
        log.info("Getting data from 3rd party api");
        return currencyFeignClient.getCurrency(API_KEY, baseCurrency);
    }

    @Override
    public CurrencyConvertResponseDto convert(CurrencyConvertDto currencyConvertDto) {
        log.info("Converting currency: {}", currencyConvertDto);
        CurrencyConvertResponseDto currencyConvertResponseDto = new CurrencyConvertResponseDto();
        String baseCurrency = currencyConvertDto.getBaseCurrency();
        String toCurrency = currencyConvertDto.getToCurrency();
        CurrencyResponseDto result = getData(baseCurrency);
        if (result.getData().containsKey(toCurrency)) {
            Map<String, Double> newResult = new HashMap<>();
            newResult.put(toCurrency, result.getData().get(toCurrency));
            currencyConvertResponseDto.setBaseCurrency(baseCurrency);
            currencyConvertResponseDto.setData(newResult);
        }
        return currencyConvertResponseDto;
    }
}

package com.example.unitech.controller;

import com.example.unitech.dto.request.CurrencyConvertDto;
import com.example.unitech.dto.response.CurrencyConvertResponseDto;
import com.example.unitech.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public ResponseEntity<CurrencyConvertResponseDto> getCurrencies(@Valid @RequestBody CurrencyConvertDto currencyConvertDto) throws ParseException {
        return ResponseEntity.ok(currencyService.convert(currencyConvertDto));
    }

}

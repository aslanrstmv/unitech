package com.example.unitech.service;

import com.example.unitech.config.FeignClientConfig;
import com.example.unitech.dto.response.CurrencyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(value = "currency", url = "http://api.exchangeratesapi.io/v1/latest", configuration = FeignClientConfig.class)
@FeignClient(value = "currency", url = "https://freecurrencyapi.net/api/v2/latest", configuration = FeignClientConfig.class)
public interface CurrencyFeignClient {

//    @GetMapping()
//    CurrencyResponseDto getCurrency(@RequestParam(value="access_key") String access_key);
    @GetMapping()
    CurrencyResponseDto getCurrency(@RequestParam(value="apikey") String apikey, @RequestParam(value="base_currency") String base_currency);
}

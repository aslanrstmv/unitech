package com.example.unitech.dto.response;

import lombok.Data;

@Data
public class SignInResponseDto {
    private String tokenType;
    private String accessToken;

    public SignInResponseDto(String accessToken) {
        this.tokenType = "Bearer";
        this.accessToken = accessToken;
    }
}

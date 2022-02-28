package com.example.unitech.dto.request;

import com.example.unitech.validation.UniquePin;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequestDto {

    @NotBlank(message = "Pin can't be empty")
    @UniquePin(message = "Pin must be unique" )
    private String pin;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, message = "Password length must be at least 6 characters")
    private String password;

}

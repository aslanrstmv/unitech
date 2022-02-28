package com.example.unitech.validation;

import com.example.unitech.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniquePinValidator implements ConstraintValidator<UniquePin, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String pin, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsUserByPin(pin);
    }
}

package com.example.unitech.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleApiException(ApiException apiException) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("Error", apiException.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException userNotFoundException) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("Error", userNotFoundException.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity usernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("Error", usernameNotFoundException.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PinException.class)
    public ResponseEntity userNotFoundException(PinException pinException) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("Error", pinException.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, String> validationErrors = new HashMap<String, String>();

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        response.put("errors", validationErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialsException(BadCredentialsException badCredentialsException) {
        return new ResponseEntity(badCredentialsException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity handleAccountException(AccountException accountException) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("Error", accountException.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity handleJwtExpireException(ExpiredJwtException expiredJwtException) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("Error", expiredJwtException.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(AccountException.class)
//    public ResponseEntity handleAccountException(AccountException accountException) {
//        Map<String, Object> response = new HashMap<String, Object>();
//        response.put("Error", accountException.getMessage());
//        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//    }

}

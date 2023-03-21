package com.example.seguridadGrand.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Validation {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> errors(MethodArgumentNotValidException ex){
        Map<String,Object> response = new HashMap<>();
        List<String> errorMap = ex.getFieldErrors()
                        .stream().map(err -> err.getDefaultMessage())
                        .collect(Collectors.toList());
        response.put("errors" , errorMap);
        response.put("mensaje","Llena lo campos restantes o escribe de la manera correcta");
        return response;
    }
}

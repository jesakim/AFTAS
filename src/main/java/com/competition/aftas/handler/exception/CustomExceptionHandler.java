package com.competition.aftas.handler.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String, String>> handleException(Throwable th){
        Map<String, String> error = new HashMap<>();
        error.put("error", "error");
        error.put("message", th.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}
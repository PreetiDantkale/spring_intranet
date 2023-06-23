package com.josh.intranet.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Map<String,String>> handleValidationException(ValidationException exception){
    Map<String,String> response = new HashMap<>();
    response.put("message", exception.getErrrorMessages().toString());
    return ResponseEntity.badRequest().body(response);
  }
}

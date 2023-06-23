package com.josh.intranet.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorUtils {

  public static List<String> getErrorMessage(BindingResult bindingResult){
    List<String> errorMessages = bindingResult.getFieldErrors().stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    return  errorMessages;
  }
}

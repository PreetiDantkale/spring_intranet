package com.josh.intranet.controller;

import com.josh.intranet.dto.request.EmployeeRequestDto;
import com.josh.intranet.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeServiceImpl employeeService;

  @PostMapping("/api/v1/employees")
  public ResponseEntity<Map> createEmployee(@Validated @RequestBody EmployeeRequestDto employeeRequestDto
      , BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = bindingResult.getFieldErrors().stream()
          .map(FieldError::getDefaultMessage)
          .collect(Collectors.toList());
      Map<String, String> response = new HashMap<>();
      response.put("message", errorMessages.toString());
      return ResponseEntity.badRequest().body(response);
    }
    employeeService.createEmployee(employeeRequestDto);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Employee Added Successfully");
    return ResponseEntity.ok().body(response);
  }
}

package com.josh.intranet.controller;

import com.josh.intranet.dto.request.EmployeeProjectRequestDto;
import com.josh.intranet.dto.request.EmployeeRequestDto;
import com.josh.intranet.service.EmployeeServiceImpl;
import com.josh.intranet.service.EmployeeProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Autowired
  EmployeeProjectServiceImpl employeeProjectService;


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

  @PostMapping("/api/v1/employees/{employee_id}/projects")
  public ResponseEntity<Map> assignProject(@PathVariable Long employee_id,
                                              @Validated @RequestBody EmployeeProjectRequestDto employeeprojectRequestDto,
                                              BindingResult bindingResult){
    employeeProjectService.updateEmployeeProject(employeeprojectRequestDto, employee_id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Project Assigned Successfully");
    return ResponseEntity.ok().body(response);
  }
}

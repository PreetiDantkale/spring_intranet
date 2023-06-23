package com.josh.intranet.controller;

import com.josh.intranet.dto.request.AddressRequestDto;
import com.josh.intranet.dto.request.EmployeeProjectRequestDto;
import com.josh.intranet.dto.request.EmployeeRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.model.Address;
import com.josh.intranet.model.Employee;
import com.josh.intranet.service.EmployeeServiceImpl;
import com.josh.intranet.service.EmployeeProjectServiceImpl;
import com.josh.intranet.utils.ErrorUtils;
import com.josh.intranet.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
      List<String> errorMessages = ErrorUtils.getErrorMessage(bindingResult);
      throw new ValidationException(errorMessages);
    }
    employeeService.createEmployee(employeeRequestDto);
    Map<String, String> response = ResponseUtils.createSuccessResponse("Employee Added Successfully");
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

  @PutMapping("/api/v1/employees/{id}")
  public Employee updateEmployee(@RequestBody EmployeeRequestDto employeeRequestDto, @PathVariable("id") Long Id) throws Exception {
    return employeeService.updateEmployee(employeeRequestDto, Id);
  }

  @GetMapping("/api/v1/employees/{id}")
  public Employee getEmployee(@PathVariable("id") Long id) throws Exception {
    return employeeService.getEmployee(id);
  }
}

package com.josh.intranet.controller;

import com.josh.intranet.dto.request.PersonalDetailsRequestDto;
import com.josh.intranet.service.PersonalDetailsServiceImpl;
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
public class PersonalDetailsController {

  @Autowired
  PersonalDetailsServiceImpl personalDetailsService;

  @PostMapping("/api/v1/employees/{employee_id}/personal_details")
  public ResponseEntity<Map> createPersonalDetails(@PathVariable("employee_id") Long employee_id,
                                            @Validated @RequestBody PersonalDetailsRequestDto personalDetailsRequestDto,
                                            BindingResult bindingResult){
    personalDetailsRequestDto.setEmployee_id(employee_id);
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = bindingResult.getFieldErrors().stream()
          .map(FieldError::getDefaultMessage)
          .collect(Collectors.toList());
      Map<String, String> response = new HashMap<>();
      response.put("message", errorMessages.toString());
      return ResponseEntity.badRequest().body(response);
    }
    personalDetailsService.createPersonalDetails(personalDetailsRequestDto);
    Map<String, String> response = new HashMap<>();
    response.put("message", "PersonalDetails Added Successfully");
    return ResponseEntity.ok().body(response);
  }
}

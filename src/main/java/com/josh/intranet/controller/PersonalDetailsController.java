package com.josh.intranet.controller;

import com.josh.intranet.dto.request.AddressRequestDto;
import com.josh.intranet.dto.request.PersonalDetailsRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.model.Address;
import com.josh.intranet.model.PersonalDetails;
import com.josh.intranet.service.PersonalDetailsServiceImpl;
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
public class PersonalDetailsController {

  @Autowired
  PersonalDetailsServiceImpl personalDetailsService;

  @PostMapping("/api/v1/employees/{employee_id}/personal_details")
  public ResponseEntity<Map> createPersonalDetails(@PathVariable("employee_id") Long employee_id,
                                            @Validated @RequestBody PersonalDetailsRequestDto personalDetailsRequestDto,
                                            BindingResult bindingResult){
    personalDetailsRequestDto.setEmployee_id(employee_id);
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = ErrorUtils.getErrorMessage(bindingResult);
      throw new ValidationException(errorMessages);
    }
    personalDetailsService.createPersonalDetails(personalDetailsRequestDto);
    Map<String, String> response = ResponseUtils.createSuccessResponse("Personal Details Added Successfully");
    return ResponseEntity.ok().body(response);
  }

  @PutMapping("/api/v1/personal_details/{id}")
  public PersonalDetails updatePersonalDetails(@RequestBody PersonalDetailsRequestDto personalDetailsRequestDto, @PathVariable("id") Long Id) throws Exception {
    return personalDetailsService.updatePersonalDetails(personalDetailsRequestDto, Id);
  }
}

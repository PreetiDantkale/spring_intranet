package com.josh.intranet.controller;

import com.josh.intranet.dto.request.SkillsRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.service.SkillsServiceImpl;
import com.josh.intranet.utils.ErrorUtils;
import com.josh.intranet.utils.ResponseUtils;
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
public class SkillsController {

  @Autowired
  SkillsServiceImpl skillsService;

  @PostMapping("/api/v1/employees/{employee_id}/skills")
  public ResponseEntity<Map> createSkills(@PathVariable("employee_id") Long employee_id,
                                            @Validated @RequestBody SkillsRequestDto skillsRequestDto,
                                            BindingResult bindingResult){
    skillsRequestDto.setEmployee_id(employee_id);
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = ErrorUtils.getErrorMessage(bindingResult);
      throw new ValidationException(errorMessages);
    }
    skillsService.createSkills(skillsRequestDto);
    Map<String, String> response = ResponseUtils.createSuccessResponse("Skills Added Successfully");
    return ResponseEntity.ok().body(response);
  }
}

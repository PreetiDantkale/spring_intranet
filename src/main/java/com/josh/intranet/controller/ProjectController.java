package com.josh.intranet.controller;

import com.josh.intranet.dto.request.ProjectRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.model.EmployeeProject;
import com.josh.intranet.service.ProjectServiceImpl;
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
public class ProjectController {

  @Autowired
  ProjectServiceImpl projectService;

  @PostMapping("/api/v1/projects")
  public ResponseEntity<Map> createProject(@Validated @RequestBody ProjectRequestDto projectRequestDto
      , BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = ErrorUtils.getErrorMessage(bindingResult);
      throw new ValidationException(errorMessages);
    }
    projectService.createProject(projectRequestDto);
    Map<String, String> response = ResponseUtils.createSuccessResponse("Project Added Successfully");
    return ResponseEntity.ok().body(response);
  }
}

package com.josh.intranet.controller;

import com.josh.intranet.dto.request.AddressRequestDto;
import com.josh.intranet.dto.request.ProjectRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.model.Address;
import com.josh.intranet.model.EmployeeProject;
import com.josh.intranet.model.Project;
import com.josh.intranet.service.ProjectServiceImpl;
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

  @PutMapping("/api/v1/projects/{id}")
  public Project updateProject(@RequestBody ProjectRequestDto projectRequestDto, @PathVariable("id") Long Id) throws Exception {
    return projectService.updateProject(projectRequestDto, Id);
  }
}

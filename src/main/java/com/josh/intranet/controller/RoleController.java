package com.josh.intranet.controller;

import com.josh.intranet.dto.request.RoleRequestDto;
import com.josh.intranet.service.RoleServiceImpl;
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
public class RoleController {

  @Autowired
  RoleServiceImpl roleService;

  @PostMapping("/api/v1/roles")
  public ResponseEntity<Map> createRole(@Validated @RequestBody RoleRequestDto roleRequestDto,
                                        BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = bindingResult.getFieldErrors().stream()
          .map(FieldError::getDefaultMessage)
          .collect(Collectors.toList());
      Map<String, String> response = new HashMap<>();
      response.put("message", errorMessages.toString());
      return ResponseEntity.badRequest().body(response);
    }
    roleService.createRole(roleRequestDto);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Role Added Successfully");
    return ResponseEntity.ok().body(response);
  }
}

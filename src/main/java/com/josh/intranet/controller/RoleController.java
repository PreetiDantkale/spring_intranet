package com.josh.intranet.controller;

import com.josh.intranet.dto.request.AddressRequestDto;
import com.josh.intranet.dto.request.RoleRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.model.Address;
import com.josh.intranet.model.Role;
import com.josh.intranet.service.RoleServiceImpl;
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
public class RoleController {

  @Autowired
  RoleServiceImpl roleService;

  @PostMapping("/api/v1/roles")
  public ResponseEntity<Map> createRole(@Validated @RequestBody RoleRequestDto roleRequestDto,
                                        BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = ErrorUtils.getErrorMessage(bindingResult);
      throw new ValidationException(errorMessages);
    }
    roleService.createRole(roleRequestDto);
    Map<String, String> response = ResponseUtils.createSuccessResponse("Role Added Successfully");;
    return ResponseEntity.ok().body(response);
  }

  @PutMapping("/api/v1/roles/{id}")
  public Role updateRole(@RequestBody RoleRequestDto roleRequestDto, @PathVariable("id") Long Id) throws Exception {
    return roleService.updateRole(roleRequestDto, Id);
  }

  @GetMapping("/api/v1/roles/{id}")
  public Role getRole(@PathVariable("id") Long id) throws Exception {
    return roleService.getRole(id);
  }

}

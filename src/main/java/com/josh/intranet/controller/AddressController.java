package com.josh.intranet.controller;

import com.josh.intranet.dto.request.AddressRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.model.Address;
import com.josh.intranet.model.Employee;
import com.josh.intranet.service.AddressServiceImpl;
import com.josh.intranet.utils.ErrorUtils;
import com.josh.intranet.utils.ResponseUtils;
import jakarta.validation.Valid;
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
public class AddressController {

  @Autowired
  AddressServiceImpl addressService;

  @PostMapping("/api/v1/employees/{employee_id}/addresses")
  public ResponseEntity<Map> createAddress(@PathVariable("employee_id") Long employee_id,
                                            @Validated @RequestBody AddressRequestDto addressRequestDto,
                                            BindingResult bindingResult){
    addressRequestDto.setEmployee_id(employee_id);
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = ErrorUtils.getErrorMessage(bindingResult);
      throw new ValidationException(errorMessages);
    }
    addressService.createAddress(addressRequestDto);
    Map<String, String> response = ResponseUtils.createSuccessResponse("Address Added Successfully");
    return ResponseEntity.ok().body(response);
  }

  @PutMapping("/api/v1/addresses/{id}")
  public Address updateAddress(@RequestBody AddressRequestDto addressRequestDto, @PathVariable("id") Long Id) throws Exception {
    return addressService.updateAddress(addressRequestDto, Id);
  }

  @GetMapping("/api/v1/addresses/{id}")
  public Address getAddress(@PathVariable("id") Long id) throws Exception {
    return addressService.getAddress(id);
  }

}

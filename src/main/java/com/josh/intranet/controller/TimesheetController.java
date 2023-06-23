package com.josh.intranet.controller;

import com.josh.intranet.dto.request.ContactDetailsRequestDto;
import com.josh.intranet.dto.request.TimesheetRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.service.ContactDetailsServiceImpl;
import com.josh.intranet.service.TimesheetServiceImpl;
import com.josh.intranet.utils.ErrorUtils;
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
public class TimesheetController {

  @Autowired
  TimesheetServiceImpl timesheetService;

  @PostMapping("/api/v1/employees/{employee_id}/timesheets")
  public ResponseEntity<Map> createTimesheet(@PathVariable("employee_id") Long employee_id,
                                            @Validated @RequestBody TimesheetRequestDto timesheetRequestDto,
                                            BindingResult bindingResult){
    timesheetRequestDto.setEmployee_id(employee_id);
    if (bindingResult.hasErrors()) {
      List<String> errorMessages = ErrorUtils.getErrorMessage(bindingResult);
      throw new ValidationException(errorMessages);
    }
    boolean saved = timesheetService.createTimesheet(timesheetRequestDto);
    Map<String, String> response = new HashMap<>();
    if(saved){
      response.put("message", "Timesheet Added Successfully");
      return ResponseEntity.ok().body(response);
    }else{
      response.put("message", "Timesheet cannot be added");
      return ResponseEntity.badRequest().body(response);
    }
  }
}

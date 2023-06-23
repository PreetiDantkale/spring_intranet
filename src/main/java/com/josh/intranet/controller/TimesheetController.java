package com.josh.intranet.controller;

import com.josh.intranet.dto.request.AddressRequestDto;
import com.josh.intranet.dto.request.ContactDetailsRequestDto;
import com.josh.intranet.dto.request.TimesheetRequestDto;
import com.josh.intranet.exception.ValidationException;
import com.josh.intranet.model.Address;
import com.josh.intranet.model.Timesheet;
import com.josh.intranet.service.ContactDetailsServiceImpl;
import com.josh.intranet.service.TimesheetServiceImpl;
import com.josh.intranet.utils.ErrorUtils;
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

  @PutMapping("/api/v1/timesheets/{id}")
  public Timesheet updateTimesheet(@RequestBody TimesheetRequestDto timesheetRequestDto, @PathVariable("id") Long Id) throws Exception {
    return timesheetService.updateTimesheet(timesheetRequestDto, Id);
  }

  @GetMapping("/api/v1/timesheets/{id}")
  public Timesheet getTimesheet(@PathVariable("id") Long id) throws Exception {
    return timesheetService.getTimesheet(id);
  }

}

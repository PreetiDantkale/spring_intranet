package com.josh.intranet.service;

import com.josh.intranet.dto.request.TimesheetRequestDto;
import com.josh.intranet.model.EmployeeProject;
import com.josh.intranet.model.Timesheet;
import com.josh.intranet.model.Employee;
import com.josh.intranet.repository.EmployeeProjectRepository;
import com.josh.intranet.repository.TimesheetRepository;
import com.josh.intranet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TimesheetServiceImpl implements TimesheetService {

  @Autowired
  TimesheetRepository timesheetRepository;
  @Autowired
  EmployeeProjectRepository employeeProjectRepository;

  private LocalDateTime timestamp;

  public boolean createTimesheet(TimesheetRequestDto timesheetRequestDto){

    Optional<EmployeeProject> employeeProject = Optional.ofNullable(employeeProjectRepository.findByProjectAndEmployeeId(timesheetRequestDto.getProject_id(),
        timesheetRequestDto.getEmployee_id()));
    Timesheet timesheetPresent = timesheetRepository.findByEmployeeProjectId(employeeProject.get().getId(),timesheetRequestDto.getDate());
    if(timesheetPresent == null) {
      Timesheet timesheet = new Timesheet();
      timesheet.setDate(timesheetRequestDto.getDate());
      timesheet.setHoursWorked(timesheetRequestDto.getHours_worked());
      timesheet.setDescription(timesheetRequestDto.getDescription());
      employeeProject.ifPresent(timesheet::setEmployeeProject);
      timestamp = LocalDateTime.now();
      timesheet.setCreatedAt(Timestamp.valueOf(timestamp));
      timesheet.setUpdatedAt(Timestamp.valueOf(timestamp));
      timesheetRepository.save(timesheet);
      return true;
    }else {
      return false;
    }
  }

  public Timesheet updateTimesheet(TimesheetRequestDto timesheetRequestDto, Long id) throws Exception {
    Optional<Timesheet> optionalTimesheet = timesheetRepository.findById(id);
    if (optionalTimesheet.isPresent()) {
      Timesheet timesheet = optionalTimesheet.get();
      timesheet.setHoursWorked(timesheetRequestDto.getHours_worked());
      timesheet.setDescription(timesheetRequestDto.getDescription());
      Timesheet updatedTimesheet = timesheetRepository.save(timesheet);
      return updatedTimesheet;
    } else {
      throw new Exception("Timesheet not found");
    }
  }

}

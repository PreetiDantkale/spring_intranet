package com.josh.intranet.service;

import com.josh.intranet.dto.request.EmployeeRequestDto;
import com.josh.intranet.model.Employee;
import com.josh.intranet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  private LocalDateTime timestamp;

  public void createEmployee(EmployeeRequestDto employeeRequestDto){
    Employee employee = new Employee();
    employee.setBloodGroup(employeeRequestDto.getBlood_group());
    employee.setContactNumber(employeeRequestDto.getContact_number());
    employee.setDob(employeeRequestDto.getDob());
    employee.setGender(employeeRequestDto.getGender());
    employee.setFirstName(employeeRequestDto.getFirst_name());
    employee.setLastName(employeeRequestDto.getLast_name());
    employee.setEmail(createEmail(employeeRequestDto.getFirst_name(), employeeRequestDto.getLast_name()));
    timestamp = LocalDateTime.now();
    employee.setCreatedAt(Timestamp.valueOf(timestamp));
    employee.setUpdatedAt(Timestamp.valueOf(timestamp));
    employeeRepository.save(employee);
  }

  private
  String createEmail(String firstName, String lastName){
    return firstName + "." + lastName + "@josh.com";
  }
}

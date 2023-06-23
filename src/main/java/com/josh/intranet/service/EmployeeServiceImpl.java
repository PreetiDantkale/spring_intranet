package com.josh.intranet.service;

import com.josh.intranet.dto.request.ContactDetailsRequestDto;
import com.josh.intranet.dto.request.EmployeeRequestDto;
import com.josh.intranet.model.ContactDetails;
import com.josh.intranet.model.Employee;
import com.josh.intranet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

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

  public Employee updateEmployee(EmployeeRequestDto employeeRequestDto, Long id) throws Exception{
    Optional<Employee> optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isPresent()) {
      Employee employee = optionalEmployee.get();
      employee.setBloodGroup(employeeRequestDto.getBlood_group());
      employee.setContactNumber(employeeRequestDto.getContact_number());
      employee.setDob(employeeRequestDto.getDob());
      employee.setGender(employeeRequestDto.getGender());
      employee.setFirstName(employeeRequestDto.getFirst_name());
      employee.setLastName(employeeRequestDto.getLast_name());
      Employee updatedEmployee = employeeRepository.save(employee);
      return updatedEmployee;
    }
    else{
      throw new Exception("Employee not found");
    }
  }

  public Employee getEmployee(Long userId) throws Exception {
    Optional<Employee> optional = employeeRepository.findById(userId);
    if(optional.isPresent()){
      return optional.get();
    }
    else{
      throw new Exception("Employee not found");
    }
  }


  private
  String createEmail(String firstName, String lastName){
    return firstName + "." + lastName + "@josh.com";
  }
}

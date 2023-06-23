package com.josh.intranet.service;

import com.josh.intranet.dto.request.ContactDetailsRequestDto;
import com.josh.intranet.model.ContactDetails;
import com.josh.intranet.model.Employee;
import com.josh.intranet.repository.ContactDetailsRepository;
import com.josh.intranet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {

  @Autowired
  ContactDetailsRepository contactDetailsRepository;
  @Autowired
  EmployeeRepository employeeRepository;

  private LocalDateTime timestamp;

  public void createContactDetails(ContactDetailsRequestDto contactDetailsRequestDto){
    ContactDetails contactDetails = new ContactDetails();
    contactDetails.setRelation(contactDetailsRequestDto.getRelation());
    contactDetails.setContactNumber(contactDetailsRequestDto.getContact_number());
    contactDetails.setName(contactDetailsRequestDto.getName());
    Optional<Employee> employee = employeeRepository.findById(contactDetailsRequestDto.getEmployee_id());
    employee.ifPresent(contactDetails::setEmployee);
    timestamp = LocalDateTime.now();
    contactDetails.setCreatedAt(Timestamp.valueOf(timestamp));
    contactDetails.setUpdatedAt(Timestamp.valueOf(timestamp));
    contactDetailsRepository.save(contactDetails);
  }
}

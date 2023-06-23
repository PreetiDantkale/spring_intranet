package com.josh.intranet.service;

import com.josh.intranet.dto.request.EmployeeRequestDto;
import com.josh.intranet.dto.request.PersonalDetailsRequestDto;
import com.josh.intranet.model.PersonalDetails;
import com.josh.intranet.model.Employee;
import com.josh.intranet.repository.PersonalDetailsRepository;
import com.josh.intranet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

  @Autowired
  PersonalDetailsRepository personalDetailsRepository;
  @Autowired
  EmployeeRepository employeeRepository;

  private LocalDateTime timestamp;

  public void createPersonalDetails(PersonalDetailsRequestDto personalDetailsRequestDto){
    PersonalDetails personalDetails = new PersonalDetails();
    personalDetails.setDoj(personalDetailsRequestDto.getDoj());
    personalDetails.setEmail(personalDetailsRequestDto.getEmail());
    personalDetails.setExperience(personalDetailsRequestDto.getExperience());
    personalDetails.setPanNumber(personalDetailsRequestDto.getPan_number());
    personalDetails.setPassport(personalDetailsRequestDto.getPassport());
    personalDetails.setPreviousCompany(personalDetailsRequestDto.getPrevious_company());
    personalDetails.setQualification(personalDetailsRequestDto.getQualification());
    personalDetails.setTShirtSize(personalDetailsRequestDto.getT_shirt_size());
    Optional<Employee> employee = employeeRepository.findById(personalDetailsRequestDto.getEmployee_id());
    employee.ifPresent(personalDetails::setEmployee);
    timestamp = LocalDateTime.now();
    personalDetails.setCreatedAt(Timestamp.valueOf(timestamp));
    personalDetails.setUpdatedAt(Timestamp.valueOf(timestamp));
    personalDetailsRepository.save(personalDetails);
  }

  public PersonalDetails updatePersonalDetails(PersonalDetailsRequestDto personalDetailsRequestDto, Long id) throws Exception {
    Optional<PersonalDetails> optionalPersonalDetails = personalDetailsRepository.findById(id);
    if (optionalPersonalDetails.isPresent()) {
      PersonalDetails personalDetails = optionalPersonalDetails.get();
      personalDetails.setDoj(personalDetailsRequestDto.getDoj());
      personalDetails.setEmail(personalDetailsRequestDto.getEmail());
      personalDetails.setExperience(personalDetailsRequestDto.getExperience());
      personalDetails.setPanNumber(personalDetailsRequestDto.getPan_number());
      personalDetails.setPassport(personalDetailsRequestDto.getPassport());
      personalDetails.setPreviousCompany(personalDetailsRequestDto.getPrevious_company());
      personalDetails.setQualification(personalDetailsRequestDto.getQualification());
      personalDetails.setTShirtSize(personalDetailsRequestDto.getT_shirt_size());
      PersonalDetails updatedPersonalDetails = personalDetailsRepository.save(personalDetails);
      return updatedPersonalDetails;
    } else {
      throw new Exception("PersonalDetails not found");
    }
  }

  public PersonalDetails getPersonalDetails(Long userId) throws Exception {
    Optional<PersonalDetails> optional = personalDetailsRepository.findById(userId);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      throw new Exception("PersonalDetails not found");
    }
  }


}

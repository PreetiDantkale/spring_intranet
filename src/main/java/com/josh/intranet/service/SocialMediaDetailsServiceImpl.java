package com.josh.intranet.service;

import com.josh.intranet.dto.request.SocialMediaDetailsRequestDto;
import com.josh.intranet.model.Employee;
import com.josh.intranet.model.SocialMediaDetails;
import com.josh.intranet.repository.EmployeeRepository;
import com.josh.intranet.repository.SocialMediaDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SocialMediaDetailsServiceImpl implements SocialMediaDetailsService {

  @Autowired
  SocialMediaDetailsRepository socialMediaDetailsRepository;
  @Autowired
  EmployeeRepository employeeRepository;

  private LocalDateTime timestamp;

  public void createSocialMediaDetails(SocialMediaDetailsRequestDto socialMediaDetailsRequestDto){
    SocialMediaDetails socialMediaDetails = new SocialMediaDetails();
    socialMediaDetails.setMediaHandle(socialMediaDetailsRequestDto.getMedia_handle());
    socialMediaDetails.setMediaName(socialMediaDetailsRequestDto.getMedia_name());
    Optional<Employee> employee = employeeRepository.findById(socialMediaDetailsRequestDto.getEmployee_id());
    employee.ifPresent(socialMediaDetails::setEmployee);
    timestamp = LocalDateTime.now();
    socialMediaDetails.setCreatedAt(Timestamp.valueOf(timestamp));
    socialMediaDetails.setUpdatedAt(Timestamp.valueOf(timestamp));
    socialMediaDetailsRepository.save(socialMediaDetails);
  }
}

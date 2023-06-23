package com.josh.intranet.service;

import com.josh.intranet.dto.request.AddressRequestDto;
import com.josh.intranet.model.Address;
import com.josh.intranet.model.Employee;
import com.josh.intranet.repository.AddressRepository;
import com.josh.intranet.repository.EmployeeRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  AddressRepository addressRepository;
  @Autowired
  EmployeeRepository employeeRepository;

  private LocalDateTime timestamp;

  public void createAddress(AddressRequestDto addressRequestDto){
    Address address = new Address();
    address.setLocation(addressRequestDto.getLocation());
    address.setContactNumber(addressRequestDto.getContact_number());
    address.setCity(addressRequestDto.getCity());
    address.setState(addressRequestDto.getState());
    address.setPinCode(addressRequestDto.getPin_code());
    address.setPermanent(addressRequestDto.is_permanent());
    Optional<Employee> employee = employeeRepository.findById(addressRequestDto.getEmployee_id());
    employee.ifPresent(address::setEmployee);
    timestamp = LocalDateTime.now();
    address.setCreatedAt(Timestamp.valueOf(timestamp));
    address.setUpdatedAt(Timestamp.valueOf(timestamp));
    addressRepository.save(address);
  }

  public Address updateAddress(Address updatedEmployee, Long id) throws Exception{
    Optional<Address> optionalAddress = addressRepository.findById(id);
    if (optionalAddress.isPresent()) {
      Address address = optionalAddress.get();
      address.setLocation(updatedEmployee.getLocation());
      address.setContactNumber(updatedEmployee.getContactNumber());
      address.setCity(updatedEmployee.getCity());
      address.setState(updatedEmployee.getState());
      address.setPinCode(updatedEmployee.getPinCode());
      address.setPermanent(updatedEmployee.isPermanent());
      Address updatedAddress = addressRepository.save(address);
      return updatedAddress;
    }
    else{
      throw new Exception("User not found");
    }
  }

}

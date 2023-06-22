package com.josh.intranet.service;

import com.josh.intranet.dto.request.RoleRequestDto;
import com.josh.intranet.model.Role;
import com.josh.intranet.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  RoleRepository roleRepository;

  private LocalDateTime timestamp;

  public void createRole(RoleRequestDto roleRequestDto){
    Role role = new Role();
    role.setName(roleRequestDto.getName());
    role.setActive(roleRequestDto.isActive());
    timestamp = LocalDateTime.now();
    role.setCreatedAt(Timestamp.valueOf(timestamp));
    role.setUpdatedAt(Timestamp.valueOf(timestamp));
    roleRepository.save(role);
  }
}

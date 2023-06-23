package com.josh.intranet.service;

import com.josh.intranet.dto.request.PersonalDetailsRequestDto;
import com.josh.intranet.dto.request.RoleRequestDto;
import com.josh.intranet.model.PersonalDetails;
import com.josh.intranet.model.Role;
import com.josh.intranet.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

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

  public Role updateRole(RoleRequestDto roleRequestDto, Long id) throws Exception {
    Optional<Role> optionalRole = roleRepository.findById(id);
    if (optionalRole.isPresent()) {
      Role role = optionalRole.get();
      role.setName(roleRequestDto.getName());
      role.setActive(roleRequestDto.isActive());
      Role updatedRole = roleRepository.save(role);
      return updatedRole;
    } else {
      throw new Exception("Role not found");
    }
  }

  public Role getRole(Long userId) throws Exception {
    Optional<Role> optional = roleRepository.findById(userId);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      throw new Exception("Role not found");
    }
  }

}

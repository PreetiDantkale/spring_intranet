package com.josh.intranet.service;

import com.josh.intranet.dto.request.SkillsRequestDto;
import com.josh.intranet.model.Employee;
import com.josh.intranet.model.Skills;
import com.josh.intranet.repository.EmployeeRepository;
import com.josh.intranet.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {

  @Autowired
  SkillsRepository skillsRepository;
  @Autowired
  EmployeeRepository employeeRepository;

  private LocalDateTime timestamp;

  public void createSkills(SkillsRequestDto skillsRequestDto){
    Skills skills = new Skills();
    skills.setPrimarySkill(skillsRequestDto.getPrimary_skill());
    skills.setSecondarySkill(skillsRequestDto.getSecondary_skill());
    skills.setTernarySkill(skillsRequestDto.getTernary_skill());
    skills.setOtherSkill(skillsRequestDto.getOther_skill());
    Optional<Employee> employee = employeeRepository.findById(skillsRequestDto.getEmployee_id());
    employee.ifPresent(skills::setEmployee);
    timestamp = LocalDateTime.now();
    skills.setCreatedAt(Timestamp.valueOf(timestamp));
    skills.setUpdatedAt(Timestamp.valueOf(timestamp));
    skillsRepository.save(skills);
  }

  public Skills updateSkills(SkillsRequestDto skillsRequestDto, Long id) throws Exception {
    Optional<Skills> optionalSkills = skillsRepository.findById(id);
    if (optionalSkills.isPresent()) {
      Skills skills = optionalSkills.get();
      skills.setPrimarySkill(skillsRequestDto.getPrimary_skill());
      skills.setSecondarySkill(skillsRequestDto.getSecondary_skill());
      skills.setTernarySkill(skillsRequestDto.getTernary_skill());
      skills.setOtherSkill(skillsRequestDto.getOther_skill());
      Skills updatedSkills = skillsRepository.save(skills);
      return updatedSkills;
    } else {
      throw new Exception("Skills not found");
    }
  }

  public Skills getSkills(Long userId) throws Exception {
    Optional<Skills> optional = skillsRepository.findById(userId);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      throw new Exception("Skills not found");
    }
  }

}

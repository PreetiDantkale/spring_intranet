package com.josh.intranet.service;

import com.josh.intranet.dto.request.ProjectRequestDto;
import com.josh.intranet.model.Project;
import com.josh.intranet.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  private LocalDateTime timestamp;

  public void createProject(ProjectRequestDto projectRequestDto){
    Project project = new Project();
    project.setName(projectRequestDto.getName());
    project.setActive(projectRequestDto.isActive());
    timestamp = LocalDateTime.now();
    project.setCreatedAt(Timestamp.valueOf(timestamp));
    project.setUpdatedAt(Timestamp.valueOf(timestamp));
    projectRepository.save(project);
  }
}

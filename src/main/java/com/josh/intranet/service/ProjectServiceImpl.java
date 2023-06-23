package com.josh.intranet.service;

import com.josh.intranet.dto.request.PersonalDetailsRequestDto;
import com.josh.intranet.dto.request.ProjectRequestDto;
import com.josh.intranet.model.PersonalDetails;
import com.josh.intranet.model.Project;
import com.josh.intranet.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

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

  public Project updateProject(ProjectRequestDto projectRequestDto, Long id) throws Exception {
    Optional<Project> optionalProject = projectRepository.findById(id);
    if (optionalProject.isPresent()) {
      Project project = optionalProject.get();
      project.setName(projectRequestDto.getName());
      project.setActive(projectRequestDto.isActive());
      Project updatedProject = projectRepository.save(project);
      return updatedProject;
    } else {
      throw new Exception("Project not found");
    }
  }

}

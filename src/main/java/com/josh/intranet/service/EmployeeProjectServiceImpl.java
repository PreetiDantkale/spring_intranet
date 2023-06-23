package com.josh.intranet.service;

import com.josh.intranet.dto.request.EmployeeProjectRequestDto;
import com.josh.intranet.dto.request.EmployeeRequestDto;
import com.josh.intranet.model.Employee;
import com.josh.intranet.model.EmployeeProject;
import com.josh.intranet.model.Project;
import com.josh.intranet.repository.EmployeeProjectRepository;
import com.josh.intranet.repository.EmployeeRepository;
import com.josh.intranet.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

  @Autowired
  EmployeeProjectRepository employeeProjectRepository;

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  ProjectRepository projectRepository;


  private LocalDateTime timestamp;

  public void updateEmployeeProject(EmployeeProjectRequestDto employeeProjectRequestDto, Long employee_id){
    List<Long> assignIds = employeeProjectRequestDto.getAssign();
    List<Long> unAssignIds = employeeProjectRequestDto.getUnassign();
    Optional<Employee> employee = employeeRepository.findById(employee_id);
    List<Long> projectIds = employee.get().getEmployeeProjectList().stream()
                                            .map(EmployeeProject::getProject)
        .map(Project::getId).collect(Collectors.toList());
    if(assignIds != null){
      for(Long projectId: assignIds){
        EmployeeProject employeeProject;
        timestamp = LocalDateTime.now();
        if(projectIds.contains(projectId)){
          employeeProject = employeeProjectRepository.findByProjectAndEmployeeId(projectId,employee_id);
          employeeProject.setActive(true);
          employeeProjectRepository.save(employeeProject);
        }else{
          employeeProject = new EmployeeProject();
          employeeProject.setEmployee(employee.orElse(null));
          Optional<Project> project = projectRepository.findById(projectId);
          employeeProject.setProject(project.orElse(null));
          employeeProject.setActive(true);
          employeeProject.setCreatedAt(Timestamp.valueOf(timestamp));
          employeeProject.setUpdatedAt(Timestamp.valueOf(timestamp));
          employeeProjectRepository.save(employeeProject);
        }
      }
    }
    if(unAssignIds != null) {
      for (Long projectId : unAssignIds) {
        EmployeeProject employeeProject;
        if (projectIds.contains(projectId)) {
          employeeProject = employeeProjectRepository.findByProjectAndEmployeeId(projectId, employee_id);
          employeeProject.setActive(false);
          employeeProjectRepository.save(employeeProject);
        }
      }
    }
  }
}

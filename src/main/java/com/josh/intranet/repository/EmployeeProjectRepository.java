package com.josh.intranet.repository;

import com.josh.intranet.model.Address;
import com.josh.intranet.model.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {

  @Query("SELECT ep from EmployeeProject ep where ep.employee.id = :EmployeeId and ep.project.id = :ProjectId")
  EmployeeProject findByProjectAndEmployeeId(Long ProjectId, Long EmployeeId);
}

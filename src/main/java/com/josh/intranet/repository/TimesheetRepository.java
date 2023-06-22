package com.josh.intranet.repository;

import com.josh.intranet.model.ContactDetails;
import com.josh.intranet.model.EmployeeProject;
import com.josh.intranet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
  @Query("SELECT t from Timesheet t where t.employeeProject.id = :employeeProjectId and date = :date")
  Timesheet findByEmployeeProjectId(Long employeeProjectId, Date date);
}

package com.josh.intranet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timesheet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Date date;

  @Column
  private int hoursWorked;

  @Column
  private String description;

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

  @ManyToOne
  private EmployeeProject employeeProject;

}

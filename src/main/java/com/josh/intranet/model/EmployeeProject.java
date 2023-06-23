package com.josh.intranet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private boolean active;

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "project_id")
  private Project project;
}

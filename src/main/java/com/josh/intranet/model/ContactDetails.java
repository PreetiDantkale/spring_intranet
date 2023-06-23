package com.josh.intranet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String relation;

  @Column
  private String name;

  @Column
  private String contactNumber;

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

  @ManyToOne
  private Employee employee;
}

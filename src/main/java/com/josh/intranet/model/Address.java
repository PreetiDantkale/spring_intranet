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
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String location;

  @Column
  private String city;

  @Column
  private String pinCode;

  @Column
  private String state;

  @Column
  private String contactNumber;

  @Column
  private boolean isPermanent;

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

  @ManyToOne
  @JsonBackReference
  private Employee employee;
}

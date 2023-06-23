package com.josh.intranet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @ManyToOne
  private Employee employee;
}

package com.josh.intranet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String panNumber;

  @Column
  private String email;

  @Column
  private String passport;

  @Column
  private String qualification;

  @Column
  private Date doj;

  @Column
  private double experience;

  @Column
  private String previousCompany;

  @Column
  private String tShirtSize;

  @OneToOne
  private Employee employee;
}

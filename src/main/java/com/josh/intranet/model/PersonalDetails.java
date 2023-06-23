package com.josh.intranet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

  @OneToOne
  @JsonBackReference
  private Employee employee;
}

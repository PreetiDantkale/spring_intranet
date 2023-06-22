package com.josh.intranet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @NotNull(message = "FirstName is required")
  private String firstName;

  @Column
  @NotNull(message = "Lastname is required")
  private String lastName;

  @Column
  @NotNull(message = "Gender is required")
  private String gender;

  @Column
  @NotNull(message = "Contact number is required")
  private String contactNumber;

  @Column
  private String bloodGroup;

  @Column
  @NotNull(message = "Dob is required")
  private Date dob;

  @Column
  @NotNull(message = "Email is required")
  private String email;

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "employee")
  private List<Address> addressList;

  @OneToMany(mappedBy = "employee")
  private List<ContactDetails> contactDetailsList;

  @OneToMany(mappedBy = "employee")
  private List<EmployeeProject> employeeProjectList;

  @OneToOne(mappedBy = "employee")
  private PersonalDetails personalDetailsList;

  @OneToMany(mappedBy = "employee")
  private List<SocialMediaDetails> socialMediaDetailsList;

  @OneToOne(mappedBy = "employee")
  private Skills skillsList;

}

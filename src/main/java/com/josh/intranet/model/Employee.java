package com.josh.intranet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String gender;

  @Column
  private String contactNumber;

  @Column
  private String bloodGroup;

  @Column
  private Date dob;

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

  @OneToOne
  private Skills skillsList;

}

package com.josh.intranet.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skills {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String primarySkill;

  @Column
  private String secondarySkill;

  @Column
  private String ternarySkill;

  @Column
  private String[] otherSkill;

  @OneToOne
  private Employee employee;

}

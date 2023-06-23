package com.josh.intranet.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

  @OneToOne
  private Employee employee;

  public static final Map<String, String> SKILLS;
  static {
    Map<String, String> skills = new HashMap<>();
    skills.put("java", "Java");
    skills.put("ruby", "Ruby");
    skills.put("python", "Python");
    SKILLS = Collections.unmodifiableMap(skills);
  }
}

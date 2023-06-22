package com.josh.intranet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @NonNull
  private String name;

  @Column
  private boolean active;

  @Column
  private Timestamp createdAt;

  @Column
  private Timestamp updatedAt;

}

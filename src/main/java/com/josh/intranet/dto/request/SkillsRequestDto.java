package com.josh.intranet.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillsRequestDto {
  @NotNull
  private String primary_skill;
  private String secondary_skill;
  private String ternary_skill;
  private String[] other_skill;
  private Long employee_id;
}

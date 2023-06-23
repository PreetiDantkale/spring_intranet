package com.josh.intranet.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetRequestDto {
  @NotNull
  private Date date;
  @Max(value=12)
  private int hours_worked;
  @NotNull
  private String description;
  private Long employee_id;
  private Long project_id;
}

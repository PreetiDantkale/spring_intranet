package com.josh.intranet.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetailsRequestDto {
  @NotNull
  private String pan_number;
  @NotNull
  private String email;
  private String passport;
  @NotNull
  private String qualification;
  @NotNull
  private Date doj;
  private String previous_company;
  private String t_shirt_size;
  private double experience;
  private Long employee_id;
}

package com.josh.intranet.dto.request;

import com.josh.intranet.model.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsRequestDto {
  @NotNull
  private String relation;
  @NotNull
  private String name;
  @NotNull
  private String contact_number;
  private Long employee_id;

}

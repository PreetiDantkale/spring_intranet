package com.josh.intranet.dto.request;

import com.josh.intranet.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {
  private Long id;
  @NotNull(message = "FirstName is required")
  private String first_name;
  @NotNull(message = "LastName is required")
  private String last_name;
  @NotNull(message = "Gender is required")
  private String gender;
  @NotNull(message = "Contact Number is required")
  private String contact_number;
  private String blood_group;
  @NotNull(message = "Dob is required")
  private Date dob;
  private String email;
}

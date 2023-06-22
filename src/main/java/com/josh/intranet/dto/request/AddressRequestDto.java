package com.josh.intranet.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {
  private Long id;
  @NotNull
  private String location;
  @NotNull
  private String city;
  @NotNull
  private String pin_code;
  @NotNull
  private String state;
  private String contact_number;
  @NotNull
  private boolean is_permanent;
  private Long employee_id;
}

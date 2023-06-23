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
public class SocialMediaDetailsRequestDto {
  @NotNull
  private String media_name;
  @NotNull
  private String media_handle;
  private Long employee_id;
}

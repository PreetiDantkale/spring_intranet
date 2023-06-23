package com.josh.intranet.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto {
  private Long id;
  private boolean active;
  @NotNull(message = "Name is required")
  private String name;
}

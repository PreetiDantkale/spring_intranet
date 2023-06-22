package com.josh.intranet.dto.request;

import com.josh.intranet.model.EmployeeProject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProjectRequestDto {

  private List<Long> assign;
  private List<Long> unassign;
  private List<Long> employeeProjectIds;
}

package edu.tltsu.medical_app.medical_app.dto.employee;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeManagerDTO {

  @Min(1L)
  private Long employeeId;

  @Min(1L)
  private Long parentId;
}

package edu.tltsu.medical_app.medical_app.dto.user;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserManagerDTO {

  @Min(1L)
  private Long userId;

  @Min(1L)
  private Long parentId;
}

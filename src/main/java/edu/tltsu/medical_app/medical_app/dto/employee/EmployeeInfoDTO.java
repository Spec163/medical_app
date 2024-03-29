package edu.tltsu.medical_app.medical_app.dto.employee;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/*
 * начальник отдела (и тп) может добавить новых пользователей из UI
 * в таком случае в parentId проставится значения изера, который отправлет форму
 * на отправителя должна тригериться проверка isAdmin (todo)
 */

@Getter
@Setter
public class EmployeeInfoDTO {

  @Min(1L)
  private Long parentId;

  @Min(1L)
  private Long departmentId;

  @NotBlank
  @Size(min = 3, max = 120)
  private String jobTitle;

  @NotNull
  private Long accountId;

  @NotBlank
  @Size(min = 1, max = 60)
  private String name;

  @NotBlank
  @Size(min = 1, max = 60)
  private String surname;

  @Size(max = 60)
  private String patronymic;

}

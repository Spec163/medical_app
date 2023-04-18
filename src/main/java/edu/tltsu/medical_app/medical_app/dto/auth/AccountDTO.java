package edu.tltsu.medical_app.medical_app.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDTO {

  @NotBlank
  @Size(min = 3, max = 25)
  private String login;

  @NotBlank
  @Size(min = 3, max = 25)
  private String password;
}

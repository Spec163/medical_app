package edu.tltsu.medical_app.medical_app.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
  private String token;
  private String role; // TODO: (Security 2) check for sensitive data
}

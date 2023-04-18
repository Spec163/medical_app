package edu.tltsu.medical_app.medical_app.controllers.auth;

import edu.tltsu.medical_app.medical_app.dto.auth.AccountDTO;
import edu.tltsu.medical_app.medical_app.dto.auth.AuthResponse;
import edu.tltsu.medical_app.medical_app.entities.Account;
import edu.tltsu.medical_app.medical_app.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("api/v1/auth")
public class AuthController {

  private final AccountService accountService;

  public AuthController(final AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/registration")
  @Deprecated
  public ResponseEntity<Account> registerUser(@Valid @RequestBody final AccountDTO registrationRequest) {
    return ResponseEntity.ok(this.accountService.saveUser(registrationRequest));
  }

  @PostMapping("/auth")
  public AuthResponse auth(@RequestBody final AccountDTO request) {
    return this.accountService.userAuth(request);
  }
}

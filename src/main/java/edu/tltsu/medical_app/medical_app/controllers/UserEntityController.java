package edu.tltsu.medical_app.medical_app.controllers;

import edu.tltsu.medical_app.medical_app.services.UserEntityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserEntityController {

  private final UserEntityService userEntityService;

  public UserEntityController(final UserEntityService userEntityService) {
    this.userEntityService = userEntityService;
  }

  // TODO: GET, PUT, POST methods and ExceptionHandler
}

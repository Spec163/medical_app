package edu.tltsu.medical_app.medical_app.controllers;

import edu.tltsu.medical_app.medical_app.exceptions.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(value = {EmployeeException.class})
  public ResponseEntity<Object> reserveException(final EmployeeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

}

package edu.tltsu.medical_app.medical_app.exceptions;

public class EmployeeException extends RuntimeException {
  public EmployeeException(final String username) {
    super(String.format("Failed to create Employee, because Employee with username = %s is already exist!", username));
  }

  public EmployeeException(final Long userId) {
    super("Employee with ID = " + userId + " not found!");
  }
}

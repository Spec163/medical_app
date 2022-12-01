package edu.tltsu.medical_app.medical_app.exceptions;

public class UserEntityException extends RuntimeException {
  public UserEntityException(final String username) {
    super(String.format("Failed to create UserEntity, because UserEntity with username = %s is already exist!", username));
  }

  public UserEntityException(final Long userId) {
    super("UserEntity with ID = " + userId + " not found!");
  }
}

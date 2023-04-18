package edu.tltsu.medical_app.medical_app.exceptions;

public class AccountException extends RuntimeException {
  public AccountException(final Long accountId) {
    super("Account with ID = " + accountId + " not found!");
  }
}

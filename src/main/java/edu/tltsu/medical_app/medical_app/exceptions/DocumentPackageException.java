package edu.tltsu.medical_app.medical_app.exceptions;

public class DocumentPackageException extends RuntimeException {

  public DocumentPackageException(final Long documentPackageId) {
    super("Document Package with ID = " + documentPackageId + " not found!");
  }
}

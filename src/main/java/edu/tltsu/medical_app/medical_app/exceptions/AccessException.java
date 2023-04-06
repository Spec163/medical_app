package edu.tltsu.medical_app.medical_app.exceptions;

import edu.tltsu.medical_app.medical_app.entities.Meeting;

public class AccessException extends RuntimeException {
  public AccessException(final Long userIdFromToken, final Long meeting, Class<Meeting> meetingClass) {
    super(String.format("Unable to modify %s with ID = %s by user = %s", meetingClass.toString(), userIdFromToken,meeting));
  }
}

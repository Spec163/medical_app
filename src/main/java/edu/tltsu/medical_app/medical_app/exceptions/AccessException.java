package edu.tltsu.medical_app.medical_app.exceptions;

import edu.tltsu.medical_app.medical_app.entities.Meeting;
import jakarta.servlet.http.HttpServletRequest;

public class AccessException extends RuntimeException {
  public AccessException(final Long userIdFromToken, final Long meeting, Class<Meeting> meetingClass) {
    super(String.format("Unable to modify %s with ID = %s by user = %s", meetingClass.toString(), userIdFromToken,meeting));
  }

  public AccessException(final Long userIdFromToken, final Long userId) {
    super(String.format("Unable to create meeting by user with ID = %s  for user = %s", userIdFromToken, userId));
  }

  public AccessException(final HttpServletRequest request) {
    super("Incorrect token in request = " + request.toString());
  }

  public AccessException(final Object accountIdFromToken) {
    super(String.format("Wrong claims {accountId=%s} in token!", accountIdFromToken));
  }
}

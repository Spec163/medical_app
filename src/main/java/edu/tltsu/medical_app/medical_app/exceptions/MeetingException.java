package edu.tltsu.medical_app.medical_app.exceptions;

public class MeetingException extends RuntimeException {

  public MeetingException(final Long meetingId) {
    super("Meeting with ID = " + meetingId + " not found!");
  }

}

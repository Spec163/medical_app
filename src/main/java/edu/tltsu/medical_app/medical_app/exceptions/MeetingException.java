package edu.tltsu.medical_app.medical_app.exceptions;

import edu.tltsu.medical_app.medical_app.utils.MeetingStatuses;

public class MeetingException extends RuntimeException {

  public MeetingException(final Long meetingId) {
    super("Meeting with ID = " + meetingId + " not found!");
  }

  public MeetingException(final Long meetingId, final MeetingStatuses status) {
    super(String.format("Meeting for user with ID = %s and status = %s not found!", meetingId, status.getMeetingStatus()));
  }

}

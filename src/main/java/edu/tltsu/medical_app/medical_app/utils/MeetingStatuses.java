package edu.tltsu.medical_app.medical_app.utils;

public enum MeetingStatuses {

  WAITING_FOR_APPROVE("WAITING_FOR_APPROVE"),
  ACCEPTED("ACCEPTED"),
  COMPLETED("COMPLETED"),
  CANCELLED("CANCELLED"),
  TERMINATED("TERMINATED"),
  ARCHIVED("ARCHIVED");

  private String meetingStatus;

  MeetingStatuses(String meetingStatus) {
    this.meetingStatus = meetingStatus;
  }

  public String getMeetingStatus() {
    return meetingStatus;
  }
}

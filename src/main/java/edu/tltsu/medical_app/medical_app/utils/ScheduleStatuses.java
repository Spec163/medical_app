package edu.tltsu.medical_app.medical_app.utils;

public enum ScheduleStatuses {

  ACTIVE("ACTIVE"),
  CANCELLED("CANCELLED"),
  ARCHIVED("ARCHIVED");

  private String scheduleStatus;

  ScheduleStatuses(String scheduleStatus) {
    this.scheduleStatus = scheduleStatus;
  }

  public String getScheduleStatus() {
    return scheduleStatus;
  }
}

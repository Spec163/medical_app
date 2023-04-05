package edu.tltsu.medical_app.medical_app.exceptions;

import java.util.Date;

public class ScheduleException extends RuntimeException {

  public ScheduleException(final Long scheduleId) {
    super("Schedule with ID = " + scheduleId + " not found!");
  }

  public ScheduleException(final Date date) {
    super("Date = " + date + " is not available!");
  }
}

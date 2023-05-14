package edu.tltsu.medical_app.medical_app.dto.meeting;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingDTO {

  @Min(1L)
  private Long employeeId;

  @Min(1L)
  private Long availableDateId;

  private String status;

  private String comment;
}

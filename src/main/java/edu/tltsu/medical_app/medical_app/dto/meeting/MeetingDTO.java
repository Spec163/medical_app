package edu.tltsu.medical_app.medical_app.dto.meeting;

import java.util.Date;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingDTO {

  @Min(1L)
  private Long userId;

  @Min(1L)
  private Long scheduleId;

  @NotNull
  private Date date;

  private String status;

  private String comment;
}

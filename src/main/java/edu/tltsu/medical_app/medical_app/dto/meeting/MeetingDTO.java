package edu.tltsu.medical_app.medical_app.dto.meeting;

import java.util.Date;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class MeetingDTO {

  @Min(1L)
  private Long employeeId;

  @Min(1L)
  private Long scheduleId;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private Date date;

  private String status;

  private String comment;
}

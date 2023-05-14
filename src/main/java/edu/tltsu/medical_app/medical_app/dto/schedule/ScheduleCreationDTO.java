package edu.tltsu.medical_app.medical_app.dto.schedule;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import edu.tltsu.medical_app.medical_app.entities.AvailableDate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class ScheduleCreationDTO {

  @NotNull
  private Long organizationId;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date startPeriod;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date endPeriod;

  @NotNull
  private Set<String> availableDates = new HashSet<>();

  @NotNull
  private Integer maxNumberOfPeople;

  private String status;

  private String comment;
}

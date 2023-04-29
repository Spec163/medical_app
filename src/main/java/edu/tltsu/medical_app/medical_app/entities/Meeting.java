package edu.tltsu.medical_app.medical_app.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "meeting")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Meeting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "meeting_id", nullable = false)
  private Long meetingId;

  // related to user entity
  @Column(name = "employee_id", nullable = false)
  private Long employeeId;

  // related to schedule
  @Column(name = "schedule_id", nullable = false)
  private Long scheduleId;


  @DateTimeFormat(pattern = "yyyy-mm-dd")
  @Column(name = "date", nullable = false)
  private Date date;

  // todo: (CLEAN_CODE) enum (ожидает подтверждения/подтверждено/пройдено/архив)
  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "comment", nullable = true)
  private String comment;
}

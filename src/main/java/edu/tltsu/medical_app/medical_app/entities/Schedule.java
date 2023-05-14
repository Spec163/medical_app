package edu.tltsu.medical_app.medical_app.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long scheduleId;

  // related to organization
  @Column(name = "organization_id", nullable = false)
  private Long organizationId;

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private Date startPeriod;

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private Date endPeriod;

  private Integer maxNumberOfPeople;

  // todo: (CLEAN_CODE) enum (ожидает подтверждения/подтверждено/пройдено/архив)
  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "comment", nullable = true)
  private String comment;

}

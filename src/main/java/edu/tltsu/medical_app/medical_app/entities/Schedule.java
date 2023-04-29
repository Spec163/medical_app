package edu.tltsu.medical_app.medical_app.entities;

import java.util.Date;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "schedule")
@Getter
@Setter
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

  private Date startPeriod;
  private Date endPeriod;
  @ElementCollection
  private Set<Date> availableDates;

//  private Integer minNumberOfPeople;
  private Integer maxNumberOfPeople;

  // todo: (CLEAN_CODE) enum (ожидает подтверждения/подтверждено/пройдено/архив)
  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "comment", nullable = true)
  private String comment;

}

package edu.tltsu.medical_app.medical_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "available_date")
@Getter
@Setter
@ToString
public class AvailableDate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long availableDateId;

  @ManyToOne
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;

  @Column(nullable = false)
  private String date;

  private Integer count;

  @Builder
  public AvailableDate(Long availableDateId, Schedule schedule, String date, Integer count) {
    this.availableDateId = availableDateId;
    this.schedule = schedule;
    this.date = date;
    this.count = count;
  }

  public AvailableDate(String date, Schedule schedule) {
    this.date = date;
    this.schedule = schedule;
  }

  public AvailableDate() {
  }
}

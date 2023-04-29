package edu.tltsu.medical_app.medical_app.entities;

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

@Entity
@Table(name = "employee")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id", nullable = false)
  private Long employeeId;

  @Column(name = "parent_id", nullable = true)
  private Long parentId;

  // todo: (Department) foreign key
  private Long departmentId;

  @Column(name = "job_title", nullable = false)
  private String jobTitle;

  @Column(nullable = false)
  private Boolean isActiveEmployee;

  @Column(name = "accountId", nullable = false, unique = true)
  private Long accountId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "surname", nullable = false)
  private String surname;

  @Column(name = "patronymic", nullable = true)
  private String patronymic;

  @Builder
  public Employee(
      final Long parentId,
      final Long departmentId,
      final String jobTitle,
      final Long accountId,
      final String name,
      final String surname,
      final String patronymic
  ) {
    this.parentId = parentId;
    this.departmentId = departmentId;
    this.jobTitle = jobTitle;
    this.isActiveEmployee = true;
    this.accountId = accountId;
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
  }
}

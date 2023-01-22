package edu.tltsu.medical_app.medical_app.entities;

import java.util.Objects;
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
import org.hibernate.Hibernate;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "parent_id", nullable = true)
  private Long parentId;

  // todo: foreign key
  private Long departmentId;

  @Column(name = "job_title", nullable = false)
  private String jobTitle;

  @Column(name = "is_active_user", nullable = false)
  private Boolean isActiveUser;

  // TODO: mb foreign key to account table
  @Column(name = "username", nullable = false, updatable = false, unique = true)
  private String username;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "surname", nullable = false)
  private String surname;

  @Column(name = "patronymic", nullable = true)
  private String patronymic;

  @Builder
  public UserEntity(
      final Long parentId,
      final Long departmentId,
      final String jobTitle,
      final String username,
      final String name,
      final String surname,
      final String patronymic
  ) {
    this.parentId = parentId;
    this.departmentId = departmentId;
    this.jobTitle = jobTitle;
    this.isActiveUser = true;
    this.username = username;
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserEntity that = (UserEntity) o;
    return userId != null && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

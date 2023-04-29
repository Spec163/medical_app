package edu.tltsu.medical_app.medical_app.repositories;

import java.util.Collection;
import java.util.List;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  List<UserEntity> findUserEntitiesByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);

  List<UserEntity> findUserEntitiesByParentId(Long parentId);

  List<UserEntity> findUserEntitiesByDepartmentId(Long departmentId);

  Boolean existsByUserId(Long userId);

  Boolean existsByAccountId(Long accountId);

  UserEntity findUserEntityByAccountId(Long accountId);

  List<UserEntity> findByUserIdNotIn(Collection<Long> scheduledUsers);

}

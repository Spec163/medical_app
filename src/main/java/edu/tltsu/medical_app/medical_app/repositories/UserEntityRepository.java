package edu.tltsu.medical_app.medical_app.repositories;

import java.util.List;
import java.util.Optional;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  List<UserEntity> findUserEntitiesByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);

  Optional<UserEntity> findUserEntityByUsername(String username);

  Boolean existsByUserId(Long userId);
  Boolean existsByUsername(String username);
}

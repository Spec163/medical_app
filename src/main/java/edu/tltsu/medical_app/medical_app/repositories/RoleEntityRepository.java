package edu.tltsu.medical_app.medical_app.repositories;

import edu.tltsu.medical_app.medical_app.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
  RoleEntity findRoleEntityByName(String name);

}

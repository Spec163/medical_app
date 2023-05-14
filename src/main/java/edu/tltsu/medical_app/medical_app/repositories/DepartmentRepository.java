package edu.tltsu.medical_app.medical_app.repositories;

import edu.tltsu.medical_app.medical_app.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

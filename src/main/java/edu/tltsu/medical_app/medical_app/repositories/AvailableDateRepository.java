package edu.tltsu.medical_app.medical_app.repositories;

import edu.tltsu.medical_app.medical_app.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
  // find by date and scheduleId
}

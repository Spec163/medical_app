package edu.tltsu.medical_app.medical_app.repositories;

import edu.tltsu.medical_app.medical_app.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  // todo: (Schedule) add native query for received available dates
}

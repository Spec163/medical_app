package edu.tltsu.medical_app.medical_app.repositories;

import java.util.List;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
  List<Meeting> findMeetingsByUserIdAndStatus(Long userId, String status);
}

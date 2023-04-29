package edu.tltsu.medical_app.medical_app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.entities.Schedule;
import edu.tltsu.medical_app.medical_app.repositories.MeetingRepository;
import edu.tltsu.medical_app.medical_app.repositories.ScheduleRepository;
import edu.tltsu.medical_app.medical_app.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutomaticDistributionService {

  private final ScheduleRepository scheduleRepository;
  private final MeetingRepository meetingRepository;
  private final EmployeeRepository employeeRepository;

  public AutomaticDistributionService(
      final ScheduleRepository scheduleRepository,
      final MeetingRepository meetingRepository,
      final EmployeeRepository employeeRepository
  ) {
    this.scheduleRepository = scheduleRepository;
    this.meetingRepository = meetingRepository;
    this.employeeRepository = employeeRepository;
  }

  public void executeAlgorithm(final Long scheduleId) {
    final Schedule schedule = this.scheduleRepository.findById(scheduleId).orElseThrow(/* todo: throw error */);
    final List<Meeting> meetings = this.meetingRepository.findMeetingsByScheduleId(scheduleId);

    final List<Date> reservedDates = new ArrayList<>();
    meetings.forEach(meeting -> reservedDates.add(meeting.getDate()));

    log.info("reservedDates: {}", reservedDates);

    final List<Long> scheduledEmployees = new ArrayList<>();
    meetings.forEach(meeting -> scheduledEmployees.add(meeting.getEmployeeId()));

    log.info("scheduledUsers: {}", scheduledEmployees);

    final List<Employee> users = this.employeeRepository.findByEmployeeIdNotIn(scheduledEmployees);

    final Map<Date, Integer> scheduleDates = new HashMap<>();
    schedule.getAvailableDates()
        .forEach(s -> scheduleDates.put(s, 0));

    log.info("Initiate scheduleDates: {}", scheduleDates);

    reservedDates.forEach(
        r -> {
          if (scheduleDates.containsKey(r)) {
            scheduleDates.put(r, scheduleDates.get(r) + 1);
          }
        }
    );
    log.info("After reserved scheduleDates: {}", scheduleDates);


  }
}

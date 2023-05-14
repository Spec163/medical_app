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
  }
}

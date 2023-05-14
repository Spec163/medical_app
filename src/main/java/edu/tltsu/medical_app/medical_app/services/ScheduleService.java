package edu.tltsu.medical_app.medical_app.services;

import java.util.HashSet;
import java.util.Set;
import edu.tltsu.medical_app.medical_app.dto.schedule.ScheduleCreationDTO;
import edu.tltsu.medical_app.medical_app.entities.AvailableDate;
import edu.tltsu.medical_app.medical_app.entities.Schedule;
import edu.tltsu.medical_app.medical_app.repositories.AvailableDateRepository;
import edu.tltsu.medical_app.medical_app.repositories.ScheduleRepository;
import edu.tltsu.medical_app.medical_app.utils.ScheduleStatuses;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final AvailableDateRepository availableDateRepository;

  public ScheduleService(
      final ScheduleRepository scheduleRepository,
      final AvailableDateRepository availableDateRepository
  ) {
    this.scheduleRepository = scheduleRepository;
    this.availableDateRepository = availableDateRepository;
  }

  public Schedule createSchedule(final ScheduleCreationDTO scheduleCreationDTO) {

    final Schedule schedule = Schedule
        .builder()
        .organizationId(scheduleCreationDTO.getOrganizationId())
        .startPeriod(scheduleCreationDTO.getStartPeriod())
        .endPeriod(scheduleCreationDTO.getEndPeriod())
        .status(ScheduleStatuses.ACTIVE.getScheduleStatus())
        .maxNumberOfPeople(scheduleCreationDTO.getMaxNumberOfPeople())
        .comment(scheduleCreationDTO.getComment())
        .build();

    this.scheduleRepository.save(schedule);

    scheduleCreationDTO.getAvailableDates().forEach(date -> this.availableDateRepository.save(new AvailableDate(date, schedule)));

    return schedule;
  }

  public Schedule getScheduleById(final Long scheduleId) {
    return this.scheduleRepository.findById(scheduleId).orElseThrow(); // todo: add exception
  }
}

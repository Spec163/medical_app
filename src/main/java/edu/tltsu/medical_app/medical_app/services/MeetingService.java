package edu.tltsu.medical_app.medical_app.services;

import java.util.Date;
import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.meeting.MeetingDTO;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.entities.Schedule;
import edu.tltsu.medical_app.medical_app.exceptions.AccessException;
import edu.tltsu.medical_app.medical_app.exceptions.MeetingException;
import edu.tltsu.medical_app.medical_app.exceptions.ScheduleException;
import edu.tltsu.medical_app.medical_app.exceptions.EmployeeException;
import edu.tltsu.medical_app.medical_app.repositories.MeetingRepository;
import edu.tltsu.medical_app.medical_app.repositories.ScheduleRepository;
import edu.tltsu.medical_app.medical_app.repositories.EmployeeRepository;
import edu.tltsu.medical_app.medical_app.utils.MeetingStatuses;
import org.springframework.stereotype.Service;

@Service // TODO: Ддобавить ограничения для записи на конкретный день - не больше 50% сотрудников с конкретного отдела
public class MeetingService {

  private final MeetingRepository meetingRepository;
  private final EmployeeRepository employeeRepository;
  private final ScheduleRepository scheduleRepository;

  public MeetingService(
      final MeetingRepository meetingRepository,
      final EmployeeRepository employeeRepository,
      final ScheduleRepository scheduleRepository
  ) {
    this.meetingRepository = meetingRepository;
    this.employeeRepository = employeeRepository;
    this.scheduleRepository = scheduleRepository;
  }

  public Meeting createMeetingForEmployee(
      final MeetingDTO meetingDTO,
      final Employee employee,
      final boolean isAdmin
  ) {
    this.checkMeetingDTOInfo(meetingDTO);
    if (!(meetingDTO.getEmployeeId().equals(employee.getEmployeeId()) || isAdmin || this.isManager(meetingDTO.getEmployeeId(), employee.getEmployeeId()))) {
      throw new AccessException(employee.getEmployeeId(), meetingDTO.getEmployeeId());
    }
    final Meeting meeting = Meeting.builder()
        .employeeId(meetingDTO.getEmployeeId())
        .scheduleId(meetingDTO.getScheduleId())
        .date(meetingDTO.getDate())
        .status(MeetingStatuses.WAITING_FOR_APPROVE.getMeetingStatus())
        .comment(meetingDTO.getComment())
        .build();

    return this.meetingRepository.save(meeting);
  }

  public Meeting cancelMeeting(final Long meetingId, final Employee employee, final boolean isAdmin) {
    final Meeting meeting = this.getMeetingById(meetingId);

    if (!(meeting.getEmployeeId().equals(employee.getEmployeeId()) || isAdmin || this.isManager(meeting.getEmployeeId(), employee.getEmployeeId()))) {
      throw new AccessException(employee.getEmployeeId(), meetingId, Meeting.class);
    }

    meeting.setStatus(MeetingStatuses.CANCELLED.getMeetingStatus());
    this.meetingRepository.save(meeting);

    return meeting;
  }

  public Meeting changeMeetingDate(
      final Long meetingId,
      final Employee employee,
      final MeetingDTO meetingDTO,
      final boolean isAdmin
  ) {
    final Meeting meeting = this.getMeetingById(meetingId);
    if (!(meeting.getEmployeeId().equals(employee.getEmployeeId()) || isAdmin || this.isManager(meeting.getEmployeeId(), employee.getEmployeeId()))) {
      throw new AccessException(employee.getEmployeeId(), meetingId, Meeting.class);
    }

    if (!this.IsAvailableMeetingDate(meetingDTO.getDate(), this.getExistingSchedule(meetingDTO.getScheduleId()))) {
      throw new ScheduleException(meetingDTO.getDate());
    }
    meeting.setDate(meeting.getDate());
    meeting.setStatus(MeetingStatuses.WAITING_FOR_APPROVE.getMeetingStatus());

    return this.meetingRepository.save(meeting);
  }

  public Meeting getMeetingById(final Long meetingId) {
    return this.meetingRepository.findById(meetingId).orElseThrow(() -> new MeetingException(meetingId));
  }

  public List<Meeting> getAllEmployeeMeetings(final Employee employee) {
    return this.meetingRepository.findMeetingsByEmployeeId(employee.getEmployeeId());
  }

  public Meeting getActiveMeeting(final Employee employee) {
    return this.meetingRepository.findMeetingByEmployeeIdAndStatus(employee.getEmployeeId(), MeetingStatuses.ACCEPTED.getMeetingStatus())
        .orElseThrow(() -> new MeetingException(employee.getEmployeeId(), MeetingStatuses.ACCEPTED));
  }

  private boolean isManager(final Long employeeId, final Long employeeIdFromToken) {
    final Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeException(employeeId));
    // TODO: Instead of `employee.getParentId().equals(employeeIdFromToken)` check by hierarchy
    return employee.getParentId().equals(employeeIdFromToken);
  }

  private void checkMeetingDTOInfo(final MeetingDTO meetingDTO) {
    if (!this.isExistingEmployee(meetingDTO.getEmployeeId())) {
      throw new EmployeeException(meetingDTO.getEmployeeId());
    }

    if (!this.IsAvailableMeetingDate(meetingDTO.getDate(), this.getExistingSchedule(meetingDTO.getScheduleId()))) {
      throw new ScheduleException(meetingDTO.getDate());
    }
  }

  private boolean isExistingEmployee(final Long employeeId) {
    return employeeRepository.existsByEmployeeId(employeeId);
  }

  private boolean IsAvailableMeetingDate(final Date date, final Schedule schedule) {
    // todo: (Meeting) check date by schedule.availableDates
    // todo: (Meeting) check number of employees for date
    return Boolean.TRUE;
  }

  private Schedule getExistingSchedule(final Long scheduleId) {
    return scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleException(scheduleId));
  }

  private boolean isExistingDocumentPackage(final Long documentPackageId) {
    // todo: (DocumentPackage) check call DocumentPackageRepository.existsByDocumentPackageId()
    return Boolean.TRUE;
  }
}

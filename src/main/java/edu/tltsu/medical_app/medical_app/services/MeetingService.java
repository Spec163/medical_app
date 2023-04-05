package edu.tltsu.medical_app.medical_app.services;

import java.util.Date;
import edu.tltsu.medical_app.medical_app.dto.meeting.MeetingDTO;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.entities.Schedule;
import edu.tltsu.medical_app.medical_app.exceptions.DocumentPackageException;
import edu.tltsu.medical_app.medical_app.exceptions.MeetingException;
import edu.tltsu.medical_app.medical_app.exceptions.ScheduleException;
import edu.tltsu.medical_app.medical_app.exceptions.UserEntityException;
import edu.tltsu.medical_app.medical_app.repositories.MeetingRepository;
import edu.tltsu.medical_app.medical_app.repositories.ScheduleRepository;
import edu.tltsu.medical_app.medical_app.repositories.UserEntityRepository;
import edu.tltsu.medical_app.medical_app.utils.MeetingStatuses;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

  private final MeetingRepository meetingRepository;
  private final UserEntityRepository userEntityRepository;
  private final ScheduleRepository scheduleRepository;

  public MeetingService(
      final MeetingRepository meetingRepository,
      final UserEntityRepository userEntityRepository,
      final ScheduleRepository scheduleRepository
  ) {
    this.meetingRepository = meetingRepository;
    this.userEntityRepository = userEntityRepository;
    this.scheduleRepository = scheduleRepository;
  }

  public Meeting createMeetingForUser(final MeetingDTO meetingDTO) {
    // todo: (Security 1) проверка обычного юзера, meetingDTO.getUserId() == token.userId
    return this.createMeeting(meetingDTO);
  }

  public Meeting createMeeting(final MeetingDTO meetingDTO) {
    this.checkMeetingDTOInfo(meetingDTO);

    final Meeting meeting = Meeting.builder()
        .userId(meetingDTO.getUserId())
        .scheduleId(meetingDTO.getScheduleId())
        .documentPackageId(meetingDTO.getDocumentPackageId())
        .date(meetingDTO.getDate())
        .status(MeetingStatuses.WAITING_FOR_APPROVE.getMeetingStatus())
        .comment(meetingDTO.getComment())
        .build();

    return this.meetingRepository.save(meeting);
  }

  public Meeting cancelMeeting(final Long meetingId, final Long userIdFromToken) {
    final Meeting meeting = this.meetingRepository.findById(meetingId).orElseThrow(() -> new MeetingException(meetingId));
    // TODO: (Security 1) meeting.userId == userIdFromToken OR userIdFromToken - manager for meeting.userId
    meeting.setStatus(MeetingStatuses.CANCELLED.getMeetingStatus());
    this.meetingRepository.save(meeting);
    return meeting;
  }

  private void checkMeetingDTOInfo(final MeetingDTO meetingDTO) {
    if (!this.isExistingUser(meetingDTO.getUserId())) {
      throw new UserEntityException(meetingDTO.getUserId());
    }

    if (!this.IsAvailableMeetingDate(meetingDTO.getDate(), this.getExistingSchedule(meetingDTO.getScheduleId()))) {
      throw new ScheduleException(meetingDTO.getDate());
    }

    if (!this.isExistingDocumentPackage(meetingDTO.getDocumentPackageId())) {
      throw new DocumentPackageException(meetingDTO.getDocumentPackageId());
    }
  }

  private boolean isExistingUser(final Long userId) {
    return userEntityRepository.existsByUserId(userId);
  }

  private boolean IsAvailableMeetingDate(final Date date, final Schedule schedule) {
    // todo: (Meeting) check date by schedule.availableDates
    // todo: (Meeting) check number of users for date
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

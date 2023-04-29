package edu.tltsu.medical_app.medical_app.services;

import java.util.Date;
import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.meeting.MeetingDTO;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.entities.Schedule;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import edu.tltsu.medical_app.medical_app.exceptions.AccessException;
import edu.tltsu.medical_app.medical_app.exceptions.MeetingException;
import edu.tltsu.medical_app.medical_app.exceptions.ScheduleException;
import edu.tltsu.medical_app.medical_app.exceptions.UserEntityException;
import edu.tltsu.medical_app.medical_app.repositories.MeetingRepository;
import edu.tltsu.medical_app.medical_app.repositories.ScheduleRepository;
import edu.tltsu.medical_app.medical_app.repositories.UserEntityRepository;
import edu.tltsu.medical_app.medical_app.utils.MeetingStatuses;
import org.springframework.stereotype.Service;

@Service // TODO: Ддобавить ограничения для записи на конкретный день - не больше 50% сотрудников с конкретного отдела
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

  public Meeting createMeetingForUser(
      final MeetingDTO meetingDTO,
      final UserEntity user,
      final boolean isAdmin
  ) {
    this.checkMeetingDTOInfo(meetingDTO);
    if (!(meetingDTO.getUserId().equals(user.getUserId()) || isAdmin || this.isManager(meetingDTO.getUserId(), user.getUserId()))) {
      throw new AccessException(user.getUserId(), meetingDTO.getUserId());
    }
    final Meeting meeting = Meeting.builder()
        .userId(meetingDTO.getUserId())
        .scheduleId(meetingDTO.getScheduleId())
        .date(meetingDTO.getDate())
        .status(MeetingStatuses.WAITING_FOR_APPROVE.getMeetingStatus())
        .comment(meetingDTO.getComment())
        .build();

    return this.meetingRepository.save(meeting);
  }

  public Meeting cancelMeeting(final Long meetingId, final UserEntity user, final boolean isAdmin) {
    final Meeting meeting = this.getMeetingById(meetingId);

    if (!(meeting.getUserId().equals(user.getUserId()) || isAdmin || this.isManager(meeting.getUserId(), user.getUserId()))) {
      throw new AccessException(user.getUserId(), meetingId, Meeting.class);
    }

    meeting.setStatus(MeetingStatuses.CANCELLED.getMeetingStatus());
    this.meetingRepository.save(meeting);

    return meeting;
  }

  public Meeting changeMeetingDate(
      final Long meetingId,
      final UserEntity user,
      final MeetingDTO meetingDTO,
      final boolean isAdmin
  ) {
    final Meeting meeting = this.getMeetingById(meetingId);
    if (!(meeting.getUserId().equals(user.getUserId()) || isAdmin || this.isManager(meeting.getUserId(), user.getUserId()))) {
      throw new AccessException(user.getUserId(), meetingId, Meeting.class);
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

  public List<Meeting> getAllUserMeetings(final UserEntity user) {
    return this.meetingRepository.findMeetingsByUserId(user.getUserId());
  }

  public Meeting getActiveMeeting(final UserEntity user) {
    return this.meetingRepository.findMeetingByUserIdAndStatus(user.getUserId(), MeetingStatuses.ACCEPTED.getMeetingStatus())
        .orElseThrow(() -> new MeetingException(user.getUserId(), MeetingStatuses.ACCEPTED));
  }

  private boolean isManager(final Long userId, final Long userIdFromToken) {
    final UserEntity user = this.userEntityRepository.findById(userId).orElseThrow(() -> new UserEntityException(userId));
    // TODO: Instead of `user.getParentId().equals(userIdFromToken)` check by hierarchy
    return user.getParentId().equals(userIdFromToken);
  }

  private void checkMeetingDTOInfo(final MeetingDTO meetingDTO) {
    if (!this.isExistingUser(meetingDTO.getUserId())) {
      throw new UserEntityException(meetingDTO.getUserId());
    }

    if (!this.IsAvailableMeetingDate(meetingDTO.getDate(), this.getExistingSchedule(meetingDTO.getScheduleId()))) {
      throw new ScheduleException(meetingDTO.getDate());
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

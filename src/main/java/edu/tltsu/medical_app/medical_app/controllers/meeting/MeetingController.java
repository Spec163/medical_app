package edu.tltsu.medical_app.medical_app.controllers.meeting;

import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.meeting.MeetingDTO;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import edu.tltsu.medical_app.medical_app.services.MeetingService;
import edu.tltsu.medical_app.medical_app.utils.TokenParserUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/meetings/user")
public class MeetingController {

  private final MeetingService meetingService;
  private final TokenParserUtils tokenParserUtils;

  public MeetingController(
      final MeetingService meetingService,
      final TokenParserUtils tokenParserUtils
  ) {
    this.meetingService = meetingService;
    this.tokenParserUtils = tokenParserUtils;
  }

  @SneakyThrows
  @GetMapping(value = "/list/all")
  public ResponseEntity<List<Meeting>> getAllUserMeetings(final HttpServletRequest request) {
    return ResponseEntity.ok(this.meetingService.getAllUserMeetings(this.tokenParserUtils.getUsersByToken(request)));
  }

  @GetMapping(value = "/{meetingId}")
  public ResponseEntity<Meeting> getMeetingById(@PathVariable("meetingId") Long meetingId) {
    return ResponseEntity.ok(this.meetingService.getMeetingById(meetingId));
  }

  @SneakyThrows
  @GetMapping(value = "/active")
  public ResponseEntity<Meeting> getActiveUserMeeting(final HttpServletRequest request) {
    return ResponseEntity.ok(this.meetingService.getActiveMeeting(this.tokenParserUtils.getUsersByToken(request)));
  }

  @SneakyThrows
  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> createMeetingForUser(
      final HttpServletRequest request,
      final @RequestBody MeetingDTO meetingDTO
  ) {
    final UserEntity user = this.tokenParserUtils.getUsersByToken(request);
    return ResponseEntity.ok(this.meetingService.createMeetingForUser(meetingDTO, user, this.tokenParserUtils.isAdmin(user.getAccountId())));
  }

  @SneakyThrows
  @PutMapping(value = "/cancel/{meetingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> cancelMeeting(
      final HttpServletRequest request,
      final @PathVariable("meetingId") Long meetingId
  ) {
    final UserEntity user = this.tokenParserUtils.getUsersByToken(request);
    return ResponseEntity.ok(this.meetingService.cancelMeeting(meetingId, user, this.tokenParserUtils.isAdmin(user.getAccountId())));
  }

  @SneakyThrows
  @PutMapping(value = "/change/date/{meetingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> changeMeeting(
      final HttpServletRequest request,
      @PathVariable("meetingId") Long meetingId,
      @RequestBody final MeetingDTO meetingDTO
  ) {
    final UserEntity user = this.tokenParserUtils.getUsersByToken(request);
    return ResponseEntity.ok(this.meetingService.changeMeetingDate(
        meetingId, user, meetingDTO, this.tokenParserUtils.isAdmin(user.getAccountId())));
  }
}

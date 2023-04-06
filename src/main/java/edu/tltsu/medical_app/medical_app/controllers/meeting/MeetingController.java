package edu.tltsu.medical_app.medical_app.controllers.meeting;

import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.meeting.MeetingDTO;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.services.MeetingService;
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
@RequestMapping("/api/v1/meetings/user")
public class MeetingController {

  private final MeetingService meetingService;

  public MeetingController(final MeetingService meetingService) {
    this.meetingService = meetingService;
  }

  @GetMapping(value = "/list/all")
  public ResponseEntity<List<Meeting>> getAllUserMeetings() {
    // TODO: (Security 1) token.userId instead of 111L
    return ResponseEntity.ok(this.meetingService.getAllUserMeetings(111L));
  }

  @GetMapping(value = "/{meetingId}")
  public ResponseEntity<Meeting> getMeetingById(@PathVariable("meetingId") Long meetingId) {
    return ResponseEntity.ok(this.meetingService.getMeetingById(meetingId));
  }

  @GetMapping(value = "/active") // active meeting by token.userId
  public ResponseEntity<Meeting> getActiveUserMeeting() {
    // TODO: (Security 1) token.userId instead of 111L
    return ResponseEntity.ok(this.meetingService.getActiveMeeting(111L));
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> createMeetingForUser(@RequestBody MeetingDTO meetingDTO) {
    // TODO: (Security 1) pre-authorize => token.userId == Meeting.userId or Admin/Manager
    return ResponseEntity.ok(this.meetingService.createMeetingForUser(meetingDTO));
  }

  @PutMapping(value = "/cancel/{meetingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> cancelMeeting(@PathVariable("meetingId") Long meetingId) {
    /* TODO:
        (Security 1) pre-authorize => token.userId == Meeting.userId or Admin/Manager
        вместо 111L нужно вытаскивать userId и дальше проверять его права
    */
    return ResponseEntity.ok(this.meetingService.cancelMeeting(meetingId, 111L));
  }

  @PutMapping(value = "/change/date/{meetingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> changeMeeting(
      @PathVariable("meetingId") Long meetingId,
      @RequestBody final MeetingDTO meetingDTO
  ) {
    // TODO: (Security 1) token.userId instead of 111L
    return ResponseEntity.ok(this.meetingService.changeMeetingDate(meetingId, 111L, meetingDTO));
  }
}

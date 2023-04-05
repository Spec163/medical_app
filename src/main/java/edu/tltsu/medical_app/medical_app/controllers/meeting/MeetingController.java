package edu.tltsu.medical_app.medical_app.controllers.meeting;

import edu.tltsu.medical_app.medical_app.dto.meeting.MeetingDTO;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.services.MeetingService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
}

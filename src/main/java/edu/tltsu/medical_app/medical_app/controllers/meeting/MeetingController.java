package edu.tltsu.medical_app.medical_app.controllers.meeting;

import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.meeting.MeetingDTO;
import edu.tltsu.medical_app.medical_app.entities.Meeting;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import edu.tltsu.medical_app.medical_app.services.AutomaticDistributionService;
import edu.tltsu.medical_app.medical_app.services.MeetingService;
import edu.tltsu.medical_app.medical_app.utils.TokenParserUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/public/meetings/employee")
public class MeetingController {

  private final MeetingService meetingService;
  private final TokenParserUtils tokenParserUtils;
  private final AutomaticDistributionService automaticDistributionService;

  public MeetingController(
      final MeetingService meetingService,
      final TokenParserUtils tokenParserUtils,
      final AutomaticDistributionService automaticDistributionService
  ) {
    this.meetingService = meetingService;
    this.tokenParserUtils = tokenParserUtils;
    this.automaticDistributionService = automaticDistributionService;
  }

  @SneakyThrows
  @GetMapping(value = "/list/all")
  public ResponseEntity<List<Meeting>> getAllEmployeeMeetings(final HttpServletRequest request) {
    return ResponseEntity.ok(this.meetingService.getAllEmployeeMeetings(this.tokenParserUtils.getEmployeesByToken(request)));
  }

  @GetMapping(value = "/{meetingId}")
  public ResponseEntity<Meeting> getMeetingById(@PathVariable("meetingId") Long meetingId) {
    return ResponseEntity.ok(this.meetingService.getMeetingById(meetingId));
  }

  @SneakyThrows
  @GetMapping(value = "/active")
  public ResponseEntity<Meeting> getActiveEmployeeMeeting(final HttpServletRequest request) {
    return ResponseEntity.ok(this.meetingService.getActiveMeeting(this.tokenParserUtils.getEmployeesByToken(request)));
  }

  @SneakyThrows
  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> createMeetingForEmployee(
      final HttpServletRequest request,
      final @RequestBody MeetingDTO meetingDTO
  ) {
    final Employee employee = this.tokenParserUtils.getEmployeesByToken(request);
    System.out.println("Employee from token: " + employee);
    return ResponseEntity.ok(this.meetingService.createMeetingForEmployee(meetingDTO, employee, this.tokenParserUtils.isAdmin(employee.getAccountId())));
  }

  @SneakyThrows
  @PutMapping(value = "/cancel/{meetingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> cancelMeeting(
      final HttpServletRequest request,
      final @PathVariable("meetingId") Long meetingId
  ) {
    final Employee employee = this.tokenParserUtils.getEmployeesByToken(request);
    return ResponseEntity.ok(this.meetingService.cancelMeeting(meetingId, employee, this.tokenParserUtils.isAdmin(employee.getAccountId())));
  }

  @SneakyThrows
  @PutMapping(value = "/change/date/{meetingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meeting> changeMeeting(
      final HttpServletRequest request,
      @PathVariable("meetingId") Long meetingId,
      @RequestBody final MeetingDTO meetingDTO
  ) {
    final Employee employee = this.tokenParserUtils.getEmployeesByToken(request);
    return ResponseEntity.ok(this.meetingService.changeMeetingDate(
        meetingId, employee, meetingDTO, this.tokenParserUtils.isAdmin(employee.getAccountId())));
  }

  // todo: move to admin methods
  @PutMapping(value = "/change/execute/{schedule}")
  public ResponseEntity<String> executeAlgorithm(final @PathVariable("schedule") Long schedule) {
    this.automaticDistributionService.executeAlgorithm(schedule);
    return ResponseEntity.ok("Completed!");
  }
}

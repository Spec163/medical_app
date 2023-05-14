package edu.tltsu.medical_app.medical_app.controllers.schedule;

import edu.tltsu.medical_app.medical_app.dto.schedule.ScheduleCreationDTO;
import edu.tltsu.medical_app.medical_app.entities.Schedule;
import edu.tltsu.medical_app.medical_app.services.ScheduleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/admin/schedule")
public class ScheduleController {
  private final ScheduleService scheduleService;

  public ScheduleController(final ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Schedule> createSchedule(@RequestBody final ScheduleCreationDTO scheduleCreationDTO) {
    return ResponseEntity.ok(this.scheduleService.createSchedule(scheduleCreationDTO));
  }

  @GetMapping("/{scheduleId}")
  public Schedule getScheduleById(@PathVariable("scheduleId") final Long scheduleId) {
    return this.scheduleService.getScheduleById(scheduleId);
  }
}

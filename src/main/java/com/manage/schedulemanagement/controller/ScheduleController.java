package com.manage.schedulemanagement.controller;

import com.manage.schedulemanagement.dto.ScheduleRequestDto;
import com.manage.schedulemanagement.dto.ScheduleResponseDto;
import com.manage.schedulemanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(scheduleRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok().body(scheduleService.getSchedule(id));
    }

    @GetMapping("")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules() {
        return ResponseEntity.ok().body(scheduleService.getSchedules());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return ResponseEntity.ok().body(scheduleService.updateSchedule(id, scheduleRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        scheduleService.deleteSchedule(id, scheduleRequestDto);
        return ResponseEntity.ok().body("일정 삭제를 성공했습니다.");
    }
}

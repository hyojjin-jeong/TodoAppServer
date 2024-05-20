package com.manage.schedulemanagement.controller;

import com.manage.schedulemanagement.dto.ScheduleRequestDto;
import com.manage.schedulemanagement.dto.ScheduleResponseDto;
import com.manage.schedulemanagement.service.ScheduleService;
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
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    @GetMapping("")
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules();
    }

    @PutMapping("/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.updateSchedule(id, scheduleRequestDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.deleteSchedule(id, scheduleRequestDto);
    }
}

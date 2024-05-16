package com.manage.schedulemanagement.service;

import com.manage.schedulemanagement.dto.ScheduleRequestDto;
import com.manage.schedulemanagement.dto.ScheduleResponseDto;
import com.manage.schedulemanagement.entity.Schedule;
import com.manage.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }
}

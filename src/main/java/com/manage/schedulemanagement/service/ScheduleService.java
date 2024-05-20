package com.manage.schedulemanagement.service;

import com.manage.schedulemanagement.dto.ScheduleRequestDto;
import com.manage.schedulemanagement.dto.ScheduleResponseDto;
import com.manage.schedulemanagement.entity.Schedule;
import com.manage.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getSchedules(){
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = checkPassword(id, scheduleRequestDto.getPassword());
        schedule.update(scheduleRequestDto);
        return id;
    }

    public Long deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = checkPassword(id, scheduleRequestDto.getPassword());
        scheduleRepository.delete(schedule);
        return id;
    }

    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
    }

    private Schedule checkPassword(Long id, String password) {
        Schedule schedule = findScheduleById(id);
        if (!Objects.equals(password, schedule.getPassword()) && schedule.getPassword() != null) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return schedule;
    }
}

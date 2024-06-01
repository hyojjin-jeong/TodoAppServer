package com.manage.schedulemanagement.controller;

import com.manage.schedulemanagement.dto.CommentRequestDto;
import com.manage.schedulemanagement.dto.CommentResponseDto;
import com.manage.schedulemanagement.entity.Schedule;
import com.manage.schedulemanagement.entity.Users;
import com.manage.schedulemanagement.security.UserDetailsImpl;
import com.manage.schedulemanagement.service.CommentService;
import com.manage.schedulemanagement.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules/{scheduleId}/comment")
public class CommentController {
    private final CommentService commentService;
    private final ScheduleService scheduleService;

    public CommentController(CommentService commentService, ScheduleService scheduleService) {
        this.commentService = commentService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("scheduleId") Long scheduleId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Users user = userDetails.getUser();

        if (scheduleId == null) {
            throw new IllegalArgumentException("일정을 선택하지 않았습니다.");
        }
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        if (schedule == null) {
            throw new IllegalArgumentException("선택한 일정은 DB에 저장되어 있지 않습니다. ");
        }
        if (requestDto.getContent() == null) {
            throw new IllegalArgumentException("댓글을 작성하지 않았습니다.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(schedule, user, requestDto));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable("scheduleId") Long scheduleId, @PathVariable("commentId") Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Users user = userDetails.getUser();

        if (scheduleId == null || commentId == null) {
            throw new IllegalArgumentException("수정할 댓글을 선택해주세요.");
        }

        Schedule schedule = scheduleService.findScheduleById(scheduleId);

        return ResponseEntity.ok().body(commentService.updateComment(schedule, commentId, user, requestDto));

    }
}

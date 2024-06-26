package com.manage.schedulemanagement.controller;

import com.manage.schedulemanagement.dto.CommentRequestDto;
import com.manage.schedulemanagement.dto.CommentResponseDto;
import com.manage.schedulemanagement.entity.Schedule;
import com.manage.schedulemanagement.entity.Users;
import com.manage.schedulemanagement.security.UserDetailsImpl;
import com.manage.schedulemanagement.service.CommentService;
import com.manage.schedulemanagement.service.ScheduleService;
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

        Schedule schedule = scheduleService.findScheduleById(scheduleId);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(schedule, user, requestDto));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable("scheduleId") Long scheduleId, @PathVariable("commentId") Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Users user = userDetails.getUser();

        Schedule schedule = scheduleService.findScheduleById(scheduleId);

        return ResponseEntity.ok().body(commentService.updateComment(schedule, commentId, user, requestDto));

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("scheduleId") Long scheduleId, @PathVariable("commentId") Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Users user = userDetails.getUser();

        commentService.deleteComment(commentId, user);

        return ResponseEntity.ok().body("댓글 삭제를 성공했습니다.");

    }
}

package com.manage.schedulemanagement.service;

import com.manage.schedulemanagement.dto.CommentRequestDto;
import com.manage.schedulemanagement.dto.CommentResponseDto;
import com.manage.schedulemanagement.entity.Comment;
import com.manage.schedulemanagement.entity.Schedule;
import com.manage.schedulemanagement.entity.Users;
import com.manage.schedulemanagement.repository.CommentRepository;
import com.manage.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto addComment(Schedule schedule, Users user, CommentRequestDto requestDto ) {
        Comment comment = new Comment(requestDto.getContent(), user, schedule);

        commentRepository.save(comment);

        return CommentResponseDto.toDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Schedule schedule, Long commentId, Users user, CommentRequestDto requestDto ) {
        if (!scheduleRepository.existsById(schedule.getId())) {
            throw new IllegalArgumentException("선택한 일정이 DB에 존재하지 않습니다.");
        }

        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);

        if (!Objects.equals(comment.getUser().getId(), user.getId())) {
            throw new IllegalArgumentException("댓글의 작성자만 수정할 수 있습니다.");
        }

        comment.update(requestDto);

        return CommentResponseDto.toDto(comment);
    }
}

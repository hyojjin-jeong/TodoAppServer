package com.manage.schedulemanagement.service;

import com.manage.schedulemanagement.dto.CommentRequestDto;
import com.manage.schedulemanagement.dto.CommentResponseDto;
import com.manage.schedulemanagement.entity.Comment;
import com.manage.schedulemanagement.entity.Schedule;
import com.manage.schedulemanagement.entity.Users;
import com.manage.schedulemanagement.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public CommentResponseDto addComment(Schedule schedule, Users user, CommentRequestDto requestDto ) {
        Comment comment = new Comment(requestDto.getContent(), user, schedule);

        repository.save(comment);

        CommentResponseDto responseDto = new CommentResponseDto(comment);
        return responseDto;
    }
}

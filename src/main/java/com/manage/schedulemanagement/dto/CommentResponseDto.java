package com.manage.schedulemanagement.dto;

import com.manage.schedulemanagement.entity.Comment;
import com.manage.schedulemanagement.entity.Schedule;
import com.manage.schedulemanagement.entity.Users;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long id;
    private String content;
    private Users user;
    private Schedule schedule;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.user = comment.getUser();
        this.schedule = comment.getSchedule();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}

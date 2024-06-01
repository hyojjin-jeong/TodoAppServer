package com.manage.schedulemanagement.dto;

import com.manage.schedulemanagement.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Long id, String content, Long userId, Long scheduleId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getUser().getId(), comment.getSchedule().getId(), comment.getCreatedAt(), comment.getModifiedAt());
    }
}

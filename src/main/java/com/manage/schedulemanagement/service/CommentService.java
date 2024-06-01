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
        Comment comment = checkUser(commentId, user);

        comment.update(requestDto);

        return CommentResponseDto.toDto(comment);
    }

    public CommentResponseDto deleteComment(Long commentId, Users user) {
        Comment comment = checkUser(commentId, user);

        commentRepository.delete(comment);

        return CommentResponseDto.toDto(comment);
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("선택한 댓글은 존재하지 않습니다."));
    }

    private Comment checkUser (Long id, Users user) {
        Comment checkComment = findCommentById(id);
        if (!Objects.equals(checkComment.getUser().getId(), user.getId())) {
            throw new IllegalArgumentException("댓글의 작성자만 수행할 수 있습니다.");
        }

        return checkComment;
    }
}

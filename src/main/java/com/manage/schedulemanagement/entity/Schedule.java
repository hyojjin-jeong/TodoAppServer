package com.manage.schedulemanagement.entity;

import com.manage.schedulemanagement.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> commentList = new ArrayList<>();

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.title = scheduleRequestDto.getTitle();
        this.contents = scheduleRequestDto.getContents();
        this.password = scheduleRequestDto.getPassword();
        this.username = scheduleRequestDto.getUsername();
    }

    public void update(ScheduleRequestDto scheduleRequestDto) {
        this.title = scheduleRequestDto.getTitle();
        this.contents = scheduleRequestDto.getContents();
    }

    public void addCommentList(Comment comment) {
        this.commentList.add(comment);
        comment.setSchedule(this);
    }
}

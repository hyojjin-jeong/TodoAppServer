package com.manage.schedulemanagement.entity;

import com.manage.schedulemanagement.dto.UsersRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class Users extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false, unique = true)
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    public Users(UsersRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.role = requestDto.getRole();
    }

    public void addCommentList(Comment comment) {
        this.commentList.add(comment);
        comment.setUser(this);
    }
}
package com.manage.schedulemanagement.dto;

import com.manage.schedulemanagement.entity.UserRoleEnum;
import com.manage.schedulemanagement.entity.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UsersResponseDto {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private UserRoleEnum role;
    private LocalDateTime createdAt;

    public UsersResponseDto(Long id, String username, String password, String nickname, UserRoleEnum role, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.createdAt = createdAt;
    }

    public static UsersResponseDto toDto(Users user) {
        return new UsersResponseDto(user.getId(), user.getUsername(), user.getPassword(), user.getNickname(), user.getRole(), user.getCreatedAt(), user.getCreatedAt());
    }
}

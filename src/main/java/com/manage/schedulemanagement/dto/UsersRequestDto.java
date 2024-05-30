package com.manage.schedulemanagement.dto;

import com.manage.schedulemanagement.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UsersRequestDto {
    private String nickname;
    private String username;
    private String password;
    private UserRoleEnum role;
}

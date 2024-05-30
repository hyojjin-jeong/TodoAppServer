package com.manage.schedulemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
}

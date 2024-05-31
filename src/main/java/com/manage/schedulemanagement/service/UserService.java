package com.manage.schedulemanagement.service;

import com.manage.schedulemanagement.dto.LoginRequestDto;
import com.manage.schedulemanagement.dto.SignupRequestDto;
import com.manage.schedulemanagement.entity.UserRoleEnum;
import com.manage.schedulemanagement.entity.Users;
import com.manage.schedulemanagement.jwt.JwtUtil;
import com.manage.schedulemanagement.repository.UsersRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UsersRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String nickname = requestDto.getNickname();

        Optional<Users> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 username 입니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 관리자로 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        Users user = new Users(nickname, username, password, role);
        userRepository.save(user);
    }

    public String login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Users user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수없습니다."));

        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("회원을 찾을 수없습니다.");
        }

        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token, res);

        return token;
    }

}

package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.request.LoginRequestDto;
import com.example.scheduledevelopproject.dto.SessionUserDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.ErrorCode;
import com.example.scheduledevelopproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ApiResponseDto<Void> loginUser(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest httpServletRequest
    ) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            session = httpServletRequest.getSession(true);
        }

        Users loginUser = userService.login(dto);
        if (loginUser != null) {
            log.info("로그인 성공 유저(id, name, email) = {}, {}, {}",
                    loginUser.getId(),
                    loginUser.getName(),
                    loginUser.getEmail());
            session.setAttribute("user", new SessionUserDto(loginUser));
            session.setMaxInactiveInterval(1800); //30분

            return ApiResponseDto.OK("로그인 성공");
        }
        return ApiResponseDto.fail(ErrorCode.LOGIN_UNAUTHORIZED); // Todo: postMan에서 401에러 뜨게하기
    }

    @GetMapping("/logout")
    public ApiResponseDto<Void> logoutUser(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // Session이 없으면 null return
        if (session != null) {
            session.invalidate();
        }
        return ApiResponseDto.OK("로그아웃 성공");
    }
}

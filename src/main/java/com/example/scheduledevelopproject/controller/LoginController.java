package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.request.LoginRequestDto;
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
        HttpSession session = httpServletRequest.getSession(false); // 없을시 null 반환(실패한 적이 있다면 세션이 있을수도)
        if (session == null) {
            session = httpServletRequest.getSession(true);  // 없을경우 있게 만들어줌
        }

        Users loginUser = userService.login(dto);
            if (loginUser != null) {
                log.info("로그인 성공 유저(id, name, email) = {}, {}, {}",
                        loginUser.getId(),
                        loginUser.getName(),
                        loginUser.getEmail());
                session.setAttribute("userId", loginUser.getId());
                session.setMaxInactiveInterval(1800);

                return ApiResponseDto.OK("로그인 성공");
            }
            return ApiResponseDto.fail(ErrorCode.LOGIN_UNAUTHORIZED); // Todo: postMan에서 401에러 뜨게하기
    }

    @GetMapping("/logout")
    public ApiResponseDto<Void> logoutUser(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        return ApiResponseDto.OK("로그아웃 성공");
    }
}

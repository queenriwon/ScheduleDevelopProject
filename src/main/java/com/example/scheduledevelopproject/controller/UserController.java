package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.annotation.SessionUser;
import com.example.scheduledevelopproject.dto.request.UserSessionDto;
import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdateNameRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdatePasswordRequestDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.dto.response.UserResponseDto;
import com.example.scheduledevelopproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponseDto<UserResponseDto> signUpUser(@Valid @RequestBody UserSignUpRequestDto dto) {
        UserResponseDto userResponseDto = userService.signUpUser(dto);
        return ApiResponseDto.OK(userResponseDto, "회원가입 성공");
    }

    @GetMapping
    public ApiResponseDto<PageResponseDto<UserResponseDto>> findAllUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponseDto<UserResponseDto> allUser = userService.findAllUser(page, size);
        return ApiResponseDto.OK(allUser, "유저 전체 조회 성공");
    }

    @GetMapping("/{userId}")
    public ApiResponseDto<UserResponseDto> findUserById(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.findUserById(userId);
        return ApiResponseDto.OK(userResponseDto, "id " + userId + " 유저 조회 성공");
    }

    @PatchMapping("/{userId}/name")
    public ApiResponseDto<UserResponseDto> updateUsername(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateNameRequestDto dto,
            @SessionUser UserSessionDto userSession
    ) {
        UserResponseDto userResponseDto = userService.updateUsername(userId, userSession.getId(), dto);
        return ApiResponseDto.OK(userResponseDto, "id " + userId + " 유저이름 수정 성공");
    }

    @PatchMapping("/{userId}/password")
    public ApiResponseDto<Void> updatePassword(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdatePasswordRequestDto dto,
            @SessionUser UserSessionDto userSession
    ) {
        userService.updatePassword(userId, userSession.getId(), dto);
        return ApiResponseDto.OK("id " + userId + " 비밀번호 수정 성공");
    }

    @PostMapping("/{userId}/delete")
    public ApiResponseDto<Void> deleteUser(
            @PathVariable Long userId,
            @RequestBody Map<String, String> password,
            @SessionUser UserSessionDto userSession
    ) {
        userService.deleteUser(userId, userSession.getId(), password.get("password"));
        return ApiResponseDto.OK("id " + userId + " 회원 탈퇴");
    }
}

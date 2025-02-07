package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdateNameRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdatePasswordRequestDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.UserResponseDto;
import com.example.scheduledevelopproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ApiResponseDto<UserResponseDto> signUpUser(@RequestBody UserSignUpRequestDto dto) {
        UserResponseDto userResponseDto = userService.signUpUser(dto);
        return ApiResponseDto.OK(userResponseDto, "회원가입 성공");
    }

    @GetMapping
    public ApiResponseDto<List<UserResponseDto>> findAllUser() {
        List<UserResponseDto> userResponseDtoList = userService.findAllUser();
        return ApiResponseDto.OK(userResponseDtoList, "유저 전체 조회 성공");
    }

    @GetMapping("/{userId}")
    public ApiResponseDto<UserResponseDto> findUserById(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.findUserById(userId);
        return ApiResponseDto.OK(userResponseDto, "id " + userId + " 유저 조회 성공");
    }

    @PatchMapping("/{userId}/name")
    public ApiResponseDto<UserResponseDto> updateUsername(
            @PathVariable Long userId,
            @RequestBody UserUpdateNameRequestDto dto) {
        UserResponseDto userResponseDto = userService.updateUsername(userId, dto);
        return ApiResponseDto.OK(userResponseDto, "id " + userId + " 유저이름 수정 성공");
    }

    @PatchMapping("/{userId}/password")
    public ApiResponseDto<Void> updatePassword(
            @PathVariable Long userId,
            @RequestBody UserUpdatePasswordRequestDto dto) {
        userService.updatePassword(userId, dto);
        return ApiResponseDto.OK("id " + userId + " 비밀번호 수정 성공");
    }

    @PostMapping("/{userId}/delete")
    public ApiResponseDto<Void> deleteUser(
            @PathVariable Long userId,
            @RequestBody Map<String,String> request) {
        userService.deleteUser(userId, request.get("password"));
        return ApiResponseDto.OK("id " + userId + " 회원 탈퇴");
    }
}

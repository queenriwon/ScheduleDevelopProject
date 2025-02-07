package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.UserResponseDto;
import com.example.scheduledevelopproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}

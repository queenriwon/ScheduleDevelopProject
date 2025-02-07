package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import com.example.scheduledevelopproject.dto.response.UserResponseDto;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.NoMatchPasswordConfirmation;
import com.example.scheduledevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto signUpUser(UserSignUpRequestDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new NoMatchPasswordConfirmation("회원가입 유저 비밀번호 확인 불일치");
        }

        Users user = new Users(dto);
        Users saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser);
    }

    public List<UserResponseDto> findAllUser() {
        List<Users> allUsers = userRepository.findAll();
        return allUsers.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    public UserResponseDto findUserById(Long id) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(id);
        return new UserResponseDto(findUser);
    }
}

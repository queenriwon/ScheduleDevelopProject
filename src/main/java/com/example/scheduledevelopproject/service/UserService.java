package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdateNameRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdatePasswordRequestDto;
import com.example.scheduledevelopproject.dto.response.UserResponseDto;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.NoMatchPasswordConfirmation;
import com.example.scheduledevelopproject.exception.custom.PasswordMismatchException;
import com.example.scheduledevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUser() {
        List<Users> allUsers = userRepository.findAll();
        return allUsers.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(id);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUsername(Long id, UserUpdateNameRequestDto dto) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(id);

        if (!findUser.getPassword().equals(dto.getPassword())) {
            throw new PasswordMismatchException("비밀번호 불일치");
        }

        findUser.updateUsers(dto.getName());
        return findUserById(id);
    }

    @Transactional
    public void updatePassword(Long id, UserUpdatePasswordRequestDto dto) {
        if (!dto.getNewPassword().equals(dto.getNewPasswordCheck())) {
            throw new NoMatchPasswordConfirmation("유저 비밀번호 확인 불일치");
        }

        Users findUser = userRepository.findUsersByIdOrElseThrow(id);

        if (!findUser.getPassword().equals(dto.getOldPassword())) {
            throw new PasswordMismatchException("기존 비밀번호와 불일치");
        }

        findUser.updatePassword(dto.getNewPassword());
    }

    @Transactional
    public void deleteUser(Long id, String password) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(id);

        log.info(findUser.getPassword());
        log.info(password);
        if (!findUser.getPassword().equals(password)) {
            throw new PasswordMismatchException("기존 비밀번호와 불일치");
        }
        userRepository.delete(findUser);
    }
}

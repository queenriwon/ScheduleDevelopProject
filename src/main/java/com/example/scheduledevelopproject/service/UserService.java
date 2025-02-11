package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.config.PasswordEncoder;
import com.example.scheduledevelopproject.dto.request.LoginRequestDto;
import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdateNameRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdatePasswordRequestDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.dto.response.UserResponseDto;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.NoMatchPasswordConfirmation;
import com.example.scheduledevelopproject.exception.custom.PasswordMismatchException;
import com.example.scheduledevelopproject.exception.custom.UnauthorizedUserAccessException;
import com.example.scheduledevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Users login(LoginRequestDto dto) {
        Users findUser = userRepository.findUsersByEmailOrElseThrow(dto.getEmail());

        if (!PasswordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new PasswordMismatchException("비밀번호 불일치");
        }
        return findUser;
    }

    @Transactional
    public UserResponseDto signUpUser(UserSignUpRequestDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new NoMatchPasswordConfirmation("회원가입 유저 비밀번호 확인 불일치");
        }

        Users user = new Users(dto);
        Users saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser);
    }

    @Transactional(readOnly = true)
    public PageResponseDto<UserResponseDto> findAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> usersPage = userRepository.findAllByOrderByModifiedAtDesc(pageable);
        Page<UserResponseDto> userResponseDtoPage = usersPage.map(UserResponseDto::new);
        return new PageResponseDto<>(userResponseDtoPage);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(id);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUsername(Long id, Long userIdBySession, UserUpdateNameRequestDto dto) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(id);

        if (!Objects.equals(userIdBySession, findUser.getId())) {
            throw new UnauthorizedUserAccessException("유저 수정 권한 없음");
        }

        findUser.updateUsers(dto.getName());
        return findUserById(id);
    }

    @Transactional
    public void updatePassword(Long id, Long userIdBySession, UserUpdatePasswordRequestDto dto) {
        if (!dto.getNewPassword().equals(dto.getNewPasswordCheck())) {
            throw new NoMatchPasswordConfirmation("유저 비밀번호 확인 불일치");
        }

        Users findUser = userRepository.findUsersByIdOrElseThrow(id);

        if (!Objects.equals(userIdBySession, findUser.getId())) {
            throw new UnauthorizedUserAccessException("유저 수정 권한 없음");
        }

        findUser.updatePassword(PasswordEncoder.encode(dto.getNewPassword()));
    }

    @Transactional
    public void deleteUser(Long id, Long userIdBySession, String password) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(id);

        if (!Objects.equals(userIdBySession, findUser.getId())) {
            throw new UnauthorizedUserAccessException("유저 수정 권한 없음");
        }
        if (!PasswordEncoder.matches(password, findUser.getPassword())) {
            throw new NoMatchPasswordConfirmation("비밀번호 불일치");
        }
        userRepository.delete(findUser);
    }

    public Users findUsersByIdOrElseThrow(Long userId) {
        return userRepository.findUsersByIdOrElseThrow(userId);
    }
}

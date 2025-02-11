package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.config.PasswordEncoder;
import com.example.scheduledevelopproject.dto.request.LoginRequestDto;
import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdateNameRequestDto;
import com.example.scheduledevelopproject.dto.request.UserUpdatePasswordRequestDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.dto.response.UserResponseDto;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.notfound.NotFoundUserByEmailException;
import com.example.scheduledevelopproject.exception.custom.badrequest.DuplicateEmailException;
import com.example.scheduledevelopproject.exception.custom.badrequest.NoMatchPasswordConfirmation;
import com.example.scheduledevelopproject.exception.custom.forbidden.ForbiddenUserAccessException;
import com.example.scheduledevelopproject.exception.custom.notfound.NotFoundUserIdException;
import com.example.scheduledevelopproject.exception.custom.unauthorized.UnauthorizedLoginException;
import com.example.scheduledevelopproject.exception.custom.unauthorized.UnauthorizedUserPasswordException;
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
        Users findUser = findUsersByEmailOrElseThrow(dto.getEmail());

        if (!PasswordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new UnauthorizedLoginException("로그인 - 가입된 유저의 비밀번호와 입력된 비밀번호 불일치");
        }
        return findUser;
    }

    @Transactional
    public UserResponseDto signUpUser(UserSignUpRequestDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new NoMatchPasswordConfirmation("회원가입 - 유저 비밀번호 확인 불일치");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("회원가입 - 이미 사용 중인 이메일");
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
        Users findUser = findUsersByIdOrElseThrow(id);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUsername(Long id, Long userIdBySession, UserUpdateNameRequestDto dto) {
        Users findUser = findUsersByIdOrElseThrow(id);

        if (!Objects.equals(userIdBySession, findUser.getId())) {
            throw new ForbiddenUserAccessException("유저 이름 수정 - 해당 로그인 회원이 수정할 수 없는 유저 정보");
        }

        findUser.updateUsers(dto.getName());
        return findUserById(id);
    }

    @Transactional
    public void updatePassword(Long id, Long userIdBySession, UserUpdatePasswordRequestDto dto) {
        if (!dto.getNewPassword().equals(dto.getNewPasswordCheck())) {
            throw new NoMatchPasswordConfirmation("유저 비밀번호 수정 - 유저 비밀번호 확인 불일치");
        }

        Users findUser = findUsersByIdOrElseThrow(id);

        if (!Objects.equals(userIdBySession, findUser.getId())) {
            throw new ForbiddenUserAccessException("유저 비밀번호 수정 - 해당 로그인 회원이 수정할 수 없는 유저 정보");
        }
        if (!PasswordEncoder.matches(dto.getOldPassword(), findUser.getPassword())) {
            throw new UnauthorizedUserPasswordException("유저 비밀번호 수정 - 가입된 유저의 비밀번호와 입력된 비밀번호 불일치");
        }

        findUser.updatePassword(PasswordEncoder.encode(dto.getNewPassword()));
    }

    @Transactional
    public void deleteUser(Long id, Long userIdBySession, String password) {
        Users findUser = findUsersByIdOrElseThrow(id);

        if (!Objects.equals(userIdBySession, findUser.getId())) {
            throw new ForbiddenUserAccessException("유저 삭제 - 해당 로그인 회원이 삭제할 수 없는 유저 정보");
        }
        if (!PasswordEncoder.matches(password, findUser.getPassword())) {
            throw new UnauthorizedUserPasswordException("유저 삭제 - 가입된 유저의 비밀번호와 입력된 비밀번호 불일치");
        }
        userRepository.delete(findUser);
    }

    public Users findUsersByIdOrElseThrow(Long userId) {
        return userRepository.findUsersById(userId).orElseThrow(() ->
                new NotFoundUserIdException("입력된 user id로 유저를 찾을 수 없음"));
    }

    private Users findUsersByEmailOrElseThrow(String email){
        return userRepository.findUsersByEmail(email).orElseThrow(() ->
                new NotFoundUserByEmailException("입력된 이메일로 유저를 찾을 수 없음"));
    }
}

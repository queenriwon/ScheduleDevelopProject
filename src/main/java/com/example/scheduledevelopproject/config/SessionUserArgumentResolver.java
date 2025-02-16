package com.example.scheduledevelopproject.config;

import com.example.scheduledevelopproject.annotation.SessionUser;
import com.example.scheduledevelopproject.dto.SessionUserDto;
import com.example.scheduledevelopproject.exception.custom.unauthorized.UnauthorizedAccessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Configuration
public class SessionUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SessionUser.class)
                && parameter.getParameterType().equals(SessionUserDto.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            session = httpServletRequest.getSession();
        }
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("user");
        if (sessionUserDto == null) {
            throw new UnauthorizedAccessException("비로그인 사용자 접근 - 로그인 필요");
        }
        return sessionUserDto;
    }
}
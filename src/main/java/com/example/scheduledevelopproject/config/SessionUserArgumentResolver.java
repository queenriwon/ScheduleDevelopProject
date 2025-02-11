package com.example.scheduledevelopproject.config;

import com.example.scheduledevelopproject.annotation.SessionUser;
import com.example.scheduledevelopproject.dto.request.UserSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Configuration
public class SessionUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SessionUser.class)
                && parameter.getParameterType().equals(UserSessionDto.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

//        // todo: null이 리턴되는 경우 어떻게 될까...
//        return session.getSession(false);
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            throw new RuntimeException("세션null에러");
        }
        return session.getAttribute("user");
    }
}

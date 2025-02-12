package com.example.scheduledevelopproject.interceptor;

import com.example.scheduledevelopproject.annotation.LoginRequired;
import com.example.scheduledevelopproject.exception.custom.unauthorized.UnauthorizedAccessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        if (requestURI.contains("/swagger-ui/") || requestURI.contains("/v3/api-docs")) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (isLoginRequired(handlerMethod)) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                throw new UnauthorizedAccessException("비로그인 사용자 접근 - 로그인 필요");
            }

            log.info("로그인된 사용자 = {}", session.getAttribute("user"));
            return true;
        }
        return true;
    }

    private boolean isLoginRequired(HandlerMethod handlerMethod){
        if (handlerMethod != null) {
            return handlerMethod.hasMethodAnnotation(LoginRequired.class);
        }
        return false;
    }
}

package com.example.scheduledevelopproject.filter;

import com.example.scheduledevelopproject.annotation.LoginRequired;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        System.out.println(requestURI);

        if (requestURI.contains("/swagger-ui/") || requestURI.contains("/v3/api-docs")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (isLoginRequired(httpRequest)) {
            HttpSession session = httpRequest.getSession(false);

            // Todo: 에러처리를 따로 해줘야됨
            if (session == null || session.getAttribute("user") == null) {
                throw new RuntimeException("로그인을 해야 이용할 수 있습니다.");
            }
            log.info("로그인된 사용자 = {}", session.getAttribute("user"));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isLoginRequired(HttpServletRequest httpServletRequest) {
        HandlerMethod handlerMethod = (HandlerMethod) httpServletRequest.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if (handlerMethod != null) {
            return handlerMethod.hasMethodAnnotation(LoginRequired.class);
        }
        return false;
    }
}

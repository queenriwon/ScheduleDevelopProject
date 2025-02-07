package com.example.scheduledevelopproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/users/signup", "/login", "/logout"};

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String responseURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (!isWhiteList(responseURI)) {
            HttpSession session = httpRequest.getSession(false);

            // Todo: 에러처리를 따로 해줘야됨
            if (session == null || session.getAttribute("userId") == null) {
                throw new RuntimeException("로그인을 해야 이용할 수 있습니다.");
            }

            log.info("로그인된 사용자 = {}", session.getAttribute("userId"));
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}

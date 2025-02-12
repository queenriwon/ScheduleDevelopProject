package com.example.scheduledevelopproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String method = httpRequest.getMethod();
        String requestURI = httpRequest. getRequestURI();
        log.info("[REQUEST] {}, {}", method, requestURI);

        filterChain.doFilter(servletRequest, servletResponse);
        int status = httpResponse.getStatus();
        log.info("[RESPONSE] 응답 완료: {}, {}, {}", method, requestURI, status);
    }
}
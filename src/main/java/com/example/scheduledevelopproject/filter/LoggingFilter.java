package com.example.scheduledevelopproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class.getName());

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String method = httpRequest.getMethod();
        String requestURI = httpRequest. getRequestURI();
        logger.info("[REQUEST] {}, {}", method, requestURI);

        filterChain.doFilter(servletRequest, servletResponse);
        int status = httpResponse.getStatus();
        logger.info("[RESPONSE] 응답 완료: {}, {}, {}", method, requestURI, status);
    }
}
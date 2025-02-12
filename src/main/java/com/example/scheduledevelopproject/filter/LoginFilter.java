package com.example.scheduledevelopproject.filter;

import com.example.scheduledevelopproject.annotation.LoginRequired;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.exception.custom.unauthorized.UnauthorizedAccessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/users/signup", "/login", "/logout"};

//    @Autowired
//    private org.springframework.context.ApplicationContext appContext;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

//        filterChain.doFilter(servletRequest, servletResponse);

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        System.out.println(requestURI);

        if (requestURI.contains("/swagger-ui/") || requestURI.contains("/v3/api-docs")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                log.info("비로그인자 접근");
                httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");

                PrintWriter writer = httpResponse.getWriter();
                writer.write(convertObjectToJson(ApiResponseDto.fail(new UnauthorizedAccessException("비로그인 사용자 접근 - 로그인을 해야 이용할 수 있습니다."))));
                writer.flush();
                writer.close();
                return;
            }

            log.info("로그인된 사용자 = {}", session.getAttribute("user"));
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }


    private boolean isLoginRequired(HttpServletRequest httpRequest){
        HandlerMethod handlerMethod = (HandlerMethod) httpRequest.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);

//        RequestMappingHandlerMapping req2HandlerMapping = (RequestMappingHandlerMapping) appContext.getBean("requestMappingHandlerMapping");
//        HandlerExecutionChain handlerExeChain = req2HandlerMapping.getHandler(httpRequest);
//        if (Objects.nonNull(handlerExeChain)) {
//            HandlerMethod handlerMethod = (HandlerMethod) handlerExeChain.getHandler();
//            Method method = handlerMethod.getMethod();
//            log.info("method={}", method);
//        }

//        Object attribute = httpRequest.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
//        log.info("httpRequest = {}", attribute);
//
//        HandlerMethod handlerMethod = (HandlerMethod) attribute;

        log.info("handlerMethod = {}", handlerMethod);

        if (handlerMethod != null) {
            log.info("isLoginRequired(1) = {}", handlerMethod.hasMethodAnnotation(LoginRequired.class));
            return handlerMethod.hasMethodAnnotation(LoginRequired.class);
        }
        log.info("isLoginRequired(2) = {}", handlerMethod != null);
        return false;
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}

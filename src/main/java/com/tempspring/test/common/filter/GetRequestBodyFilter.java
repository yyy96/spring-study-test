package com.tempspring.test.common.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

/**
 * 장애 알림 봇에서 사용할 필터
 * - RequestBody 를 ContentCachingRequestWrapper 에 세팅해서
 * - "ExceptionControllerAdvice" WebRequest 에서 꺼내 사용할 수 있음
 */
@Component
public class GetRequestBodyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);

        chain.doFilter(contentCachingRequestWrapper, response);
    }
}


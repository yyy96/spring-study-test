package com.tempspring.test.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 서블릿 필터를 통해 로그인 하지 않은 사용자는 나머지 경로에 들어갈 수 없다.
 * 공통 관심사를 Servlet Filter 를 사용해서 해결한 덕분에 향후 로그인 관련 정책 변경시 해당 부분만 변경하면 된다.
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Http 요청이 오면 doFilter 가 호출된다
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // ServletRequest 에서 받는 모든 종류의 요청 ex. FTP, STOMP 같은 요청의 경우는 고려 x
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 미인증 사용자 체크
        HttpSession session = httpRequest.getSession(false);
//        if (session == null || session.getAttribute("USER_LOGIN") == null) {
//            httpResponse.sendRedirect("https://zum.com");
//            // 더 이상 필터 및 서블릿, 컨트롤러를 진행하지 않는다.
//            // return;
//            throw new IllegalStateException("다시 로그인 해주세요.");
//        }

        //다음 체인 필터를 진행한다.
        //필터 체인 목록과 순서는 FilterConfig 에서 정의했다.

        log.info("filter(1) - login filter init");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

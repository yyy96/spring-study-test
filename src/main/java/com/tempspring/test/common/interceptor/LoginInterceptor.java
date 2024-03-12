package com.tempspring.test.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 인터셉터는 스프링 MVC 구조에 특화된 필터 기능을 제공한다
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * Controller 호출 전 호출
     * 현재 테스트를 위해 Filter 와 동일한 로직 작성/비교
     */
    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {
        // 미인증 사용자 체크
//        HttpSession session = httpRequest.getSession(false);
//        if (session == null || session.getAttribute("USER_LOGIN") == null) {
//            httpResponse.sendRedirect("https://zum.com");
//            // 더 이상 필터 및 서블릿, 컨트롤러를 진행하지 않는다.
//            // return;
//            throw new IllegalStateException("다시 로그인 해주세요.");
//        }

        // 일반적인 @Controller 핸들러 매핑을 사용할 경우 HandlerMethod 를 통해 핸들러 정보 넘어옴.
        // 호출할 컨트롤러 메서드에 대한 정보 출력
        if (handler instanceof HandlerMethod) {
            log.info("Request Handler : " + handler);
        }
        // 정상 호출 반환
        log.info("preHandle(1) - LoginInterceptor");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        String requestUrl = request.getRequestURI();
        log.info("afterCompletion(1) - LoginInterceptor");
        log.info("requestUrl : " + requestUrl);
    }
}

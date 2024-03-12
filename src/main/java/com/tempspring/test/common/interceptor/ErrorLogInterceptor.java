package com.tempspring.test.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 인터셉터 체인 확인
 */
@Slf4j
public class ErrorLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle(2) - ErrorLogInterceptor");
        return true;
    }

    /**
     * afterCompletion 은 예외가 발생해도 호출된다. (언제든, 항상)
     * 예외(ex)를 파라미터로 받아서 어떤 예외가 발생했는지 로그를 출력한다.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        if (ex != null) {
            System.err.println("[INTERCEPTOR ERROR MESSAGE] " + ex.getMessage());
            System.err.println("[HTTP 응답 상태 코드] " + response.getStatus());
            ex.printStackTrace(); // 예외 스택 트레이스 출력
        }
        log.info("afterCompletion(2) - ErrorLogInterceptor");
    }
}

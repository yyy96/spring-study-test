package com.tempspring.test.common.resolver;

import com.tempspring.test.common.annotation.AuthLogin;
import com.tempspring.test.user.dto.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Spring 에서 파라미터를 공통으로 처리할 수 있도록 구현된 인터페이스
 * Interceptor 요청 뒤에 이뤄지며,
 * Controller(API Endpoint) 로 들어온 데이터(파라미터)를 가공하는 로직이 필요할 경우에 사용한다.
 */
@Slf4j
@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * @return true 일시 ArgumentResolver 가 실행된다
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("ArgumentResolver - LoginResolver supportsParameter start");

        boolean hasLoginAnnotation = parameter.hasMethodAnnotation(AuthLogin.class);
        boolean hasLoginRequest = parameter.getParameterType().equals(UserLoginRequest.class);
        //return hasLoginAnnotation || hasLoginRequest;
        return true;
    }

    /**
     * 컨트롤러 호출 전에 호출 되어서 필요한 파라미터 정보를 생성하거나 수정한다
     *
     * @return Object 이때 반환된 객체는 컨트롤러 호출시 함께 전달해준다
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        log.info("ArgumentResolver - LoginResolver resolveArgument start");

        //TODO: 비즈니스 로직 다시 작성하기
//        // HTTP 요청에서 Body를 읽어서 비밀번호 혹은 JWT Token 추출 및 복호화
//        String pwd = webRequest.getParameter("userPwd");
//
//        // 가공 후 전달
//        return UserLoginRequest.builder()
//                .userPwd(pwd)
//                .build();

        return null;
    }
}

package com.tempspring.test.common.config;

import com.tempspring.test.common.interceptor.ErrorLogInterceptor;
import com.tempspring.test.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 여러 개의 인터셉터 체인을 등록한다.
 *
 * WebMvcConfigurer 를 상속받기 때문에 WebConfig 로 한번에 묶을 수 있지만,
 * 추후 재학습시 보기 편리함을 위해 설정 파일 분리
 * */
@Configuration
public class InterCeptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new ErrorLogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }
}

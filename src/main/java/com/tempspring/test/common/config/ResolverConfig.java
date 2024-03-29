package com.tempspring.test.common.config;

import com.tempspring.test.common.resolver.LoginArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 뷰 리졸버에 대한 등록
 * WebMvcConfigurer 를 상속받기 때문에 WebConfig 로 한번에 묶을 수 있지만,
 * 추후 재학습시 보기 편리함을 위해 설정 파일 분리
 */
@Configuration
public class ResolverConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }
}

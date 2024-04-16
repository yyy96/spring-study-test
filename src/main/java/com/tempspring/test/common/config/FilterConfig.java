package com.tempspring.test.common.config;

import com.tempspring.test.common.filter.GetRequestBodyFilter;
import com.tempspring.test.common.filter.LoginFilter;
import com.tempspring.test.common.filter.UserCheckFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 여러 개의 Filter 를 등록할 경우 bean 으로 등록하여 사용한다.
 * (단일 Filter 의 경우 @ServletComponentScan 사용)
 */
@Configuration
public class FilterConfig {


    /**
     * 첫번째 필터
     * 로그인 여부 확인
     */
    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new LoginFilter()); // 등록할 필터를 지정
        filter.setOrder(1);                  // 필터에 대한 (체인)순서 지정
        filter.addUrlPatterns("/*");         // 필터를 적용할 URL 필터 지정
        return filter;
    }

    /**
     * 두번째 필터
     * ZUM 회원인지 EST 통합 회원인지 확인
     */
    @Bean
    public FilterRegistrationBean zumUserFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new UserCheckFilter());
        filter.setOrder(2);
        filter.addUrlPatterns("/*");
        return filter;
    }

    /**
     * 장애 알림 봇에서 사용할 필터
     */
    @Bean
    public FilterRegistrationBean requestBodyRappingFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new GetRequestBodyFilter());
        filter.setOrder(3);
        filter.addUrlPatterns("/*");
        return filter;
    }
}

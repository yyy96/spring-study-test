package com.tempspring.test.config;

import com.tempspring.test.common.exception.FeignClientErrorDecoderException;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> new FeignClientErrorDecoderException();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

    /**
     * 1. NONE: 로깅이 비활성화
     * 2. BASIC: 요청 메소드와 URL, 응답 상태 코드, 요청 시간만 로깅
     * 3. HEADERS: BASIC 수준의 정보 + 요청 및 응답 헤더가 로깅
     * 4. FULL: 요청과 응답의 본문, 헤더, 메타데이터까지 모두 로깅
     * */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
package com.tempspring.test.adapter.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return (methodKey, response) -> new FeignClientErrorDecoderException(FEIGN_CLIENT_ERROR);
//    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
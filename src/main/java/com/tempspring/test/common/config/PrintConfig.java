package com.tempspring.test.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PrintConfig {

    private static final String PROD = "prod";
    private static final String STG = "stg";
    private static final String LOCAL = "local";

    @Bean
    @Profile(PROD)
    public StartPrint prodPrint() {
        System.out.println(PROD + " 상태로 실행합니다.");
        return new StartPrint();
    }

    @Bean
    @Profile(STG)
    public StartPrint stgPrint() {
        System.out.println(STG + " 상태로 실행합니다.");
        return new StartPrint();
    }

    @Bean
    @Profile(LOCAL)
    public StartPrint localPrint() {
        System.out.println(LOCAL + " 상태로 실행합니다.");
        return new StartPrint();
    }

    @Bean
    public StartPrint testPrint() {
        System.out.println("기타 test 입니다..");
        return new StartPrint();
    }
}

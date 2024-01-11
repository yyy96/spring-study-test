package com.tempspring.test.common.config;

import com.tempspring.test.common.constant.SystemMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PrintConfig {
    private static final String PRINT_BEAN_NAME = "printLongBean";

    @Bean(PRINT_BEAN_NAME)
    @Profile(SystemMode.PROD)
    public StartPrint prodPrint() {
        System.out.println(SystemMode.PROD + " 상태로 실행합니다.");
        return new StartPrint();
    }

    @Bean(PRINT_BEAN_NAME)
    @Profile(SystemMode.STG)
    public StartPrint stgPrint() {
        System.out.println(SystemMode.STG + " 상태로 실행합니다.");
        return new StartPrint();
    }

    @Bean
    @Profile(SystemMode.LOCAL)
    public StartPrint localPrint() {
        System.out.println(SystemMode.LOCAL + " 상태로 실행합니다.");
        return new StartPrint();
    }

    @Bean
    public StartPrint testPrint() {
        System.out.println("기타 test 입니다..");
        return new StartPrint();
    }
}

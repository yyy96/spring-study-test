package com.tempspring.test.pay.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class NaverPayService extends PayService2 {

    public void childToParent(){
        this.pay();
        this.pay_final();
    }

    /**
     * final 이 없으면 부모 클래스를 재정의 가능하다.
     */
    @Override
    @Cacheable
    public void pay(){
        System.out.println("네이버페이");
    }

    /**
     * CGLIB 의 특징
     * final 은 상속에서 재정의할 수 없다.
     */
//    @Override
//    public void pay_final(){
//
//    }

}

package com.tempspring.test.order.service;

import com.tempspring.test.delivery.DeliveryService;
import com.tempspring.test.pay.service.KakaoPayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderService {

    private KakaoPayService kakaoPayService;

    private DeliveryService deliveryService;

    @Transactional
    public void order() {
        System.out.println("주문 요청");
        kakaoPayService.inPay();
        System.out.println("주문 완료");
    }

    @Transactional
    public void transactionOrder() {
        System.out.println("주문 요청");
        kakaoPayService.inTransactionPay();
        System.out.println("주문 완료");
    }

    @Transactional
    public void orderWithDelivery() {
        System.out.println("주문 요청");
        kakaoPayService.inTransactionPay();
        deliveryService.delivery();
        System.out.println("주문 완료");
    }

    @Transactional
    public void orderWithDeliveryFail() {
        System.out.println("주문 요청");
        kakaoPayService.inTransactionPay();
        try{
            deliveryService.deliveryFail();
        }catch (RuntimeException e){
            System.out.println("배달 매칭 실패");
        }
        System.out.println("주문 완료");
    }
}

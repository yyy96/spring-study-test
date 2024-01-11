package com.tempspring.test.order.controller;

import com.tempspring.test.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    /**
     * 트랜잭션 전파 확인하기
     * */
    @GetMapping()
    public void order(){
        orderService.order();
    }

    @GetMapping("/transaction")
    public void order2(){
        orderService.transactionOrder();
    }

    @GetMapping("/delivery")
    public void orderWithDelivery(){
        orderService.orderWithDelivery();
    }

    @GetMapping("/delivery/fail")
    public void orderWithDeliveryFail(){
        orderService.orderWithDeliveryFail();
    }
}

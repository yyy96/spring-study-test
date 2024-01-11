package com.tempspring.test.delivery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeliveryService {

    @Transactional
    public void delivery() {
        System.out.println("배달 매칭 성공");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) //이게 없으면 트랜잭션 전파에 의해 상위 트랜잭션에서 exception을 캐치하는 것과 상관없이 전체가 rollback됨.
    public void deliveryFail() {
        throw new RuntimeException();
    }
}

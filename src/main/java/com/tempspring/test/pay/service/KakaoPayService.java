package com.tempspring.test.pay.service;

import com.tempspring.test.common.transactions.TransactionLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KakaoPayService implements PayService1 {
    @Override
    public void pay() {
        System.out.println("카카오페이");
    }

    public void exPay() {
        System.out.println("[외부 호출] 결제 완료");
        TransactionLog.printActiveInfo();
        inTransactionPay();
    }

    @Transactional
    public void exTransactionPay() {
        System.out.println("[외부 호출] 트랜잭션 결제 완료");
        TransactionLog.printActiveInfo();
        inPay();
    }

    public void inPay() {
        System.out.println("[내부 호출] 결제 완료");
        TransactionLog.printActiveInfo();
    }

    @Transactional
    public void inTransactionPay() {
        System.out.println("[내부 호출] 트랜잭션 결제 완료");
        TransactionLog.printActiveInfo();
    }

    /**
     * 자바 상속과 관련해서 private 은 안된다.
     */
//    @Transactional
//    private void privatePay(){
//        System.out.println("private 으로 AOP 시도");
//        printTransactionInfo();
//    }

}

package com.tempspring.test.common.transactions;

import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 트랜잭션 상태 출력 Log
 * */
public class TransactionLog {

    public static void printActiveInfo() {
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("isTransactionActive = " + isTxActive);
    }

    public static void printRollbackInfo() {
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("isTransactionActive = " + isTxActive);
    }
}

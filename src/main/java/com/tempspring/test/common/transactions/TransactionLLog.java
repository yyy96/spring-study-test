package com.tempspring.test.common.transactions;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionLLog {

    public static void printActiveInfo() {
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("isTransactionActive = " + isTxActive);
    }

    public static void printRollbackInfo() {
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("isTransactionActive = " + isTxActive);
    }
}

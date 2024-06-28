package com.tempspring.test.slack.service;

import com.tempspring.test.common.exception.BusinessException;
import com.tempspring.test.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SlackTestService {

    public void testSendErrorMessage() {
        throw new BusinessException(ErrorCode.BUSINESS_ERROR);
    }
}
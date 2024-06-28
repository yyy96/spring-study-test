package com.tempspring.test.slack.sender;

import com.tempspring.test.slack.dto.ErrorAlertMessage;

public interface ErrorSender {
    void send(ErrorAlertMessage message);
}
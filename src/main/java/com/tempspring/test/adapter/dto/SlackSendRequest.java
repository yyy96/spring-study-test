package com.tempspring.test.adapter.dto;

public record SlackSendRequest(
        String text
) {
    public static SlackSendRequest of(String message) {
        return new SlackSendRequest(message);
    }
}
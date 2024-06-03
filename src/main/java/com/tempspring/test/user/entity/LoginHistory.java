package com.tempspring.test.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginHistory extends UserBaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;


    @Builder
    public LoginHistory(String userId) {
        this.userId = userId;
    }

    public static LoginHistory create(String userId) {
        return LoginHistory.builder()
                .userId(userId)
                .build();
    }
}
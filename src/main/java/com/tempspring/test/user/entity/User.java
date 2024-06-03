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
public class User extends UserBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Boolean isFirstLogin;


    @Builder
    public User(String userId, String userPassword, String name) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
    }


    public static User create(String userId, String userPassword, String name) {
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .name(name)
                .build();
    }

    public void updateLoginStatus(){
        this.isFirstLogin = false;
    }
}
package com.tempspring.test.user.service;

import com.tempspring.test.user.dto.UserLoginRequest;
import com.tempspring.test.user.entity.LoginHistory;
import com.tempspring.test.user.entity.LoginHistoryRepository;
import com.tempspring.test.user.entity.User;
import com.tempspring.test.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    /**
     * Transaction
     * 자기 호출(Self-Invocation) 이슈 확인
     */
    @Transactional
    public void login_origin(UserLoginRequest request) {
        Optional<User> member = userRepository.findByUserId(request.getUserId());

        if (member.isPresent()) {
            member.get().updateLoginStatus();
        } else {
            userRepository.save(User.create(request.getUserId(), request.getUserPwd(), request.getName()));
        }
        loginHistoryRepository.save(new LoginHistory(request.getUserId()));

        // 동일한 Exception 발생시 트랜잭션 확인
        throw new RuntimeException();
    }

    @Transactional
    public void longin_self_invocation(UserLoginRequest request) {
        Optional<User> member = userRepository.findByUserId(request.getUserId());

        if (member.isPresent()) {
            isMember(member.get());
        } else {
            isNotMember(request.getUserId(), request.getUserPwd(), request.getName());
        }
        loginHistoryRepository.save(new LoginHistory(request.getUserId()));

        // 동일한 Exception 발생시 트랜잭션 확인
        throw new RuntimeException();
    }

    private void isMember(User user) {
        user.updateLoginStatus();
    }

    private void isNotMember(String userId, String pwd, String name) {
        userRepository.save(User.create(userId, pwd, name));
    }

}
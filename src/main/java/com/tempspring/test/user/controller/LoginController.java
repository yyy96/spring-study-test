package com.tempspring.test.user.controller;

import com.tempspring.test.user.dto.UserLoginRequest;
import com.tempspring.test.user.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginService userService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody UserLoginRequest userLoginRequest
    ) {
        userService.login_origin(userLoginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login/self-invocation")
    public ResponseEntity login_self_invocation(
            @RequestBody UserLoginRequest userLoginRequest
    ) {
        userService.longin_self_invocation(userLoginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
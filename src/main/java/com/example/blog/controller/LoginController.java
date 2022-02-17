package com.example.blog.controller;

import com.example.blog.common.R;
import com.example.blog.entity.PasswordChangeRequest;
import com.example.blog.entity.User;
import com.example.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public R login(@RequestBody User user) {
        return loginService.login(user);
    }

    @RequestMapping("/user/logout")
    public R logout() {
        return loginService.logout();
    }

    @PostMapping("/user/changePassword")
    public R changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        return loginService.changePassword(passwordChangeRequest);
    }
}

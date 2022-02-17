package com.example.blog.service.impl;

import com.example.blog.common.R;
import com.example.blog.entity.LoginUser;
import com.example.blog.entity.PasswordChangeRequest;
import com.example.blog.entity.User;
import com.example.blog.service.LoginService;
import com.example.blog.service.UserService;
import com.example.blog.utils.JwtUtil;
import com.example.blog.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserService userService;

    @Override
    public R login(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(usernamePasswordAuthenticationToken)) {
            throw new RuntimeException("Login failed");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("login:"+userId, loginUser);
        return R.success().data("token", jwt).data("username", loginUser.getUser().getName()).data("permissions", loginUser.getPermissions());
    }

    @Override
    public R logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userId);
        return R.success().message("Logout successfully");
    }

    @Override
    public R changePassword(PasswordChangeRequest passwordChangeRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(passwordChangeRequest.getOld(), user.getPassword())) {
            if (!passwordChangeRequest.getPass().equals(passwordChangeRequest.getCheckPass())) {
                return R.error();
            }
            user.setPassword(passwordChangeRequest.getPass());
            userService.updateUserById(user);
            redisCache.deleteObject("login:"+user.getId());
            return R.success().message("Changed password successfully");
        } else {
            return R.error();
        }
    }
}

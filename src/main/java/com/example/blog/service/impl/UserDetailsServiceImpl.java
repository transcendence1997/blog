package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.entity.LoginUser;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", s);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        List<String> permissions = userService.getPermissionsByUserId(user.getId());
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}

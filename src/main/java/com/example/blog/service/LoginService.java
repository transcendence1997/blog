package com.example.blog.service;

import com.example.blog.common.R;
import com.example.blog.entity.PasswordChangeRequest;
import com.example.blog.entity.User;

public interface LoginService {
    R login(User user);

    R logout();

    R changePassword(PasswordChangeRequest passwordChangeRequest);
}

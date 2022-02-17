package com.example.blog.handler;

import com.alibaba.fastjson.JSON;
import com.example.blog.common.R;
import com.example.blog.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        R result = R.error().code(HttpStatus.UNAUTHORIZED.value()).message("Authentication failed");
        String s = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, s);
    }
}

package com.example.blog.entity;

import lombok.Data;

@Data
public class PasswordChangeRequest {

    private String old;
    private String pass;
    private String checkPass;
}

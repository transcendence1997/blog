package com.example.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDetail {
    private String id;

    private String title;

    private String description;

    private String content;

    private Date gmtCreate;

    private Date gmtUpdate;
}

package com.example.blog.service;

import com.example.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.ArticleDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-18
 */
public interface ArticleService extends IService<Article> {

    ArticleDetail getArticleDetailById(String id);

    boolean addArticleDetail(ArticleDetail articleDetail);

    boolean updateArticleDetail(ArticleDetail articleDetail);

    boolean deleteArticleById(String id);
}

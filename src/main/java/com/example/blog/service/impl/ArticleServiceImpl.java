package com.example.blog.service.impl;

import com.example.blog.entity.Article;
import com.example.blog.entity.ArticleContent;
import com.example.blog.entity.ArticleDetail;
import com.example.blog.mapper.ArticleMapper;
import com.example.blog.service.ArticleContentService;
import com.example.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleContentService articleContentService;

    @Override
    public ArticleDetail getArticleDetailById(String id) {
        Article article = baseMapper.selectById(id);
        ArticleContent articleContent = articleContentService.getById(id);
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(article, articleDetail);
        BeanUtils.copyProperties(articleContent, articleDetail);
        return articleDetail;
    }

    @Override
    public boolean addArticleDetail(ArticleDetail articleDetail) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDetail, article);
        int result = baseMapper.insert(article);
        if (result == 0) {
            return false;
        }
        ArticleContent articleContent = new ArticleContent();
        BeanUtils.copyProperties(articleDetail, articleContent);
        articleContent.setId(article.getId());
        return articleContentService.save(articleContent);
    }

    @Override
    public boolean updateArticleDetail(ArticleDetail articleDetail) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDetail, article);
        int result = baseMapper.updateById(article);
        if (result == 0) {
            return false;
        }
        ArticleContent articleContent = new ArticleContent();
        BeanUtils.copyProperties(articleDetail, articleContent);
        return articleContentService.updateById(articleContent);
    }

    @Override
    public boolean deleteArticleById(String id) {
        int result = baseMapper.deleteById(id);
        if (result == 0) {
            return false;
        }
        return articleContentService.removeById(id);
    }
}

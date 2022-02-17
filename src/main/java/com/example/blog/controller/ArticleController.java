package com.example.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.common.R;
import com.example.blog.entity.Article;
import com.example.blog.entity.ArticleDetail;
import com.example.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public R getArticleList() {
        return R.success().data("items", articleService.list(new QueryWrapper<Article>().orderByDesc("gmt_create")));
    }

    @GetMapping("/detail/{id}")
    public R getArticleDetail(@PathVariable String id) {
        return R.success().data("item", articleService.getArticleDetailById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Article Management')")
    public R addArticle(@RequestBody ArticleDetail articleDetail) {
        boolean flag = articleService.addArticleDetail(articleDetail);
        if (flag) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('Article Management')")
    public R updateArticle(@RequestBody ArticleDetail articleDetail) {
        boolean flag = articleService.updateArticleDetail(articleDetail);
        if (flag) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Article Management')")
    public R deleteArticle(@PathVariable String id) {
        boolean flag = articleService.deleteArticleById(id);
        if (flag) {
            return R.success();
        } else {
            return R.error();
        }
    }
}


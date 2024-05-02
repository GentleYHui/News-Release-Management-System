package com.yhui.controller;

import com.yhui.pojo.Article;
import com.yhui.pojo.PageBean;
import com.yhui.pojo.Result;
import com.yhui.service.ArticleService;
import com.yhui.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController{
    @Autowired
    private ArticleService articleService;

    //新增文章
    @PostMapping
   public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
   }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
}

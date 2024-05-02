package com.yhui.service;

import com.yhui.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);

    //查询所有分类
    List<Category> list();

    //根据Id查询分类信息
    Category findById(Integer id);

    //更新分类信息
    void update(Category category);
}

package org.eu.qiao.myspringboot.service;


import org.eu.qiao.myspringboot.mapper.CategoryMapper;
import org.eu.qiao.myspringboot.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNamg CategoryService
 * @Description todo
 * Author BOB
 * @Date 2019/4/2 16:08
 * @Version 1.0
 **/
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询所有的分类
     *
     * @return
     */
    public List<Category> list() {
        List<Category> categories = categoryMapper.findAll();
        for(Category category: categories){
            System.out.println(category.getName());
        }
        return categories;
    }

    /**
     * 根据分类名称获取一个分类
     * @param name
     * @return
     */
    public Category findByName(String name) {
        return categoryMapper.findByName(name);
    }
}



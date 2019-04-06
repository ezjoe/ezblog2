package org.eu.qiao.myspringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.eu.qiao.myspringboot.pojo.Category;


import java.util.List;


@Mapper
public interface CategoryMapper {

    public Category findByName(String name);

    public List<Category> findAll();

}

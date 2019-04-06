package org.eu.qiao.myspringboot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.eu.qiao.myspringboot.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    public List<Article> findAll();

    public List<Article> findAllByCategoryId(String id);

    public List<Article> findByTitleLike(String keyWord);

    public void save(Article article);

    public void deleteById(String id);

    public Article getOne(String id);


}

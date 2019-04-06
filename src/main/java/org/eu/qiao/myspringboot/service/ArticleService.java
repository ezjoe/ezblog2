package org.eu.qiao.myspringboot.service;

import org.eu.qiao.myspringboot.mapper.CategoryMapper;
import org.eu.qiao.myspringboot.pojo.Article;
import org.eu.qiao.myspringboot.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassNamg ArticleService
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 17:26
 * @Version 1.0
 **/
@Service
public class ArticleService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public List<Article> list() {
        List<Article> articles = articleMapper.findAll();
        for(Article article:articles){
            System.out.println(article.getTitle());
        }
        return articles;
    }

    /**
     * save article
     *
     * @param article
     */
    public void save(Article article) {
        articleMapper.save(article);
    }

    /***
     * delete article
     * @param id
     */
    public void delete(String id) {
        articleMapper.deleteById(id);
    }

    /***
     * get article by id
     * @param id
     * @return
     */
    public Article getById(String id) {
        return articleMapper.getOne(id);
    }

    /***
     * get articles by category name
     * @param categoryName
     * @return
     */
    public List<Article> getArticleByCategoryName(String categoryName) {

        return articleMapper.findAllByCategoryId(categoryMapper.findByName(categoryName).getId());
    }

    /***
     * search by keyword
     * @param keyWord
     * @return
     */
    public List<Article> search(String keyWord) {
        return articleMapper.findByTitleLike(keyWord);
    }

}

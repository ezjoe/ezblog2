package org.eu.qiao.myspringboot.controller;

import org.eu.qiao.myspringboot.pojo.Article;
import org.eu.qiao.myspringboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * @ClassNamg ArticleController
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 17:24
 * @Version 1.0
 **/
@Controller
//@RequestMapping("/article")
public class FrontController {

    @Autowired
    ArticleService articleService;

    /***
     * index page
     * @param model
     * @return
     */
    @RequestMapping(value={"","/article"})
    public String list(Model model) {
        List<Article> articles = articleService.list();
        model.addAttribute("articles", articles);
        return "front/index";
    }

    @RequestMapping("article/get/{id}")
    public String get(Model model, @PathVariable(name = "id") String id) {
        return "index";
    }

    /***
     * category page
     * @param dispalyname
     * @param category
     * @param model
     * @return
     */
    @RequestMapping("/article/column/{displayname}/{category}")
    public String column(@PathVariable("displayname") String dispalyname, @PathVariable("category") String category, Model model) {
        model.addAttribute("articles", articleService.getArticleByCategoryName(category));
        model.addAttribute("displayName", dispalyname);
        return "front/columnPage";
    }


    /***
     * article detail page
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") String id, Model model) {
        System.out.println(id);
        Article article = articleService.getById(id);
        System.out.println(article.getId());
        Markdown markdown = new Markdown();
        try {
            StringWriter out = new StringWriter();
            markdown.transform(new StringReader(article.getContent()), out);
            out.flush();
            article.setContent(out.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("article", article);
        return "front/detail";
    }

    /***
     * search page
     * @param keyWord
     * @param model
     * @return
     */
    @RequestMapping("/article/search")
    public String search(String keyWord, Model model) {
        List<Article> articles = articleService.search(keyWord);
        for(Article article: articles){
            System.out.println(article.getTitle());
        }
        model.addAttribute("articles", articles);
        return "front/search";
    }
}

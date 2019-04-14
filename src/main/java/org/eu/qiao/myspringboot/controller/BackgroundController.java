package org.eu.qiao.myspringboot.controller;

import org.eu.qiao.myspringboot.config.WebSecurityConfig;
import org.eu.qiao.myspringboot.pojo.Article;
import org.eu.qiao.myspringboot.pojo.Category;
import org.eu.qiao.myspringboot.pojo.User;
import org.eu.qiao.myspringboot.service.ArticleService;
import org.eu.qiao.myspringboot.service.CategoryService;
import org.eu.qiao.myspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassNamg AdminController
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 20:15
 * @Version 1.0
 **/


@Controller
@RequestMapping("/admin")
public class BackgroundController {

    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /***
     * 登录后，后台主页
     * @param model
     * @return
     */
    @RequestMapping("")
    public String admin(Model model){
        List<Article> articles = articleService.list();
        model.addAttribute("articles", articles);
        return "admin/index";
    }

    /***
     * 登录模块
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/admin/login";
    }


    /**
     * 登录验证
     * @param user
     * @param model
     * @return
     */
    @PostMapping(value = "/dologin")
    public String doLogin(HttpServletResponse response, HttpServletRequest request, User user, Model model){

        if(userService.login(user.getUsername(), user.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("user",user.getUsername());
            model.addAttribute("user", user);
            return "redirect:/admin/";
        }else {
            model.addAttribute("error", "用户名或者密码错误");
            System.out.println("failture");
            return "admin/login";
        }
    }

    /***
     * pages for writing article
     * @param model
     * @return
     */
    @RequestMapping("/write")
    public String write(Model model){
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
        model.addAttribute("article", new Article());
        return "admin/write";
    }


    @PostMapping(value = "/save")
    public String save(Article article){
        //设置种类
        String name = article.getCategory().getName();
        Category category = categoryService.findByName(name);
        article.setCategory(category);
        System.out.println(category.getId());
        //设置摘要,取前40个字
        if(article.getContent().length() > 40){
            article.setSummary(article.getContent().substring(0, 40));
        }else {
            article.setSummary(article.getContent());
        }
        article.setDate(sdf.format(new Date()));
        articleService.save(article);

        return "redirect:/admin";
    }


    /**
     * delete article
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        articleService.delete(id);
        return "redirect:/admin";
    }

    /***
     * update article
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model){
        Article article = articleService.getById(id);
        model.addAttribute("target", article);
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
        model.addAttribute("article", new Article());
        return "admin/update";
    }


}

package org.eu.qiao.myspringboot.pojo;


import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;

/**
 * @ClassNamg Article
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 21:56
 * @Version 1.0
 **/
public class Article implements Serializable {

    private String id;

    private String title;

    private String content;

    @Autowired
    private Category category;

    private String summary;

    private String date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}

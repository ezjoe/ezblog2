package org.eu.qiao.myspringboot.pojo;

import java.io.Serializable;

/**
 * @ClassNamg Category
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 21:56
 * @Version 1.0
 **/
public class Category implements Serializable {

    private String id;

    private String name;

    private String displayName;

    public Category(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

package org.eu.qiao.myspringboot.pojo;

import java.io.Serializable;


/**
 * @ClassNamg User
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 21:56
 * @Version 1.0
 **/

public class User implements Serializable {


    private String id;
    private String username;// 用户姓名
    private String password;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

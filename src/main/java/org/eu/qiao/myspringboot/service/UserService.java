package org.eu.qiao.myspringboot.service;

import org.eu.qiao.myspringboot.mapper.UserMapper;
import org.eu.qiao.myspringboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassNamg UserService
 * @Description todo
 * Author BOB
 * @Date 2019/4/1 20:28
 * @Version 1.0
 **/
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public boolean login(String username, String password){
        User user = userMapper.findByUsernameAndPassword(username, password);
        if(null == user){
            return false;
        }else {
            return true;
        }
    }

}

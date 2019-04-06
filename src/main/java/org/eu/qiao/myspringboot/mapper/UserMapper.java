package org.eu.qiao.myspringboot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.eu.qiao.myspringboot.pojo.User;

@Mapper
public interface UserMapper {

        /***
         *
         * @param username
         * @param password
         * @return
         */
        User findByUsernameAndPassword(String username, String password);



}

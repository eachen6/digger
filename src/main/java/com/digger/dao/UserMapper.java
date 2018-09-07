package com.digger.dao;

import org.apache.ibatis.annotations.Param;

import com.digger.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User todologin(@Param("username")String username,@Param("password")String password);
}
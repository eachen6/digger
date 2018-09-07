package com.digger.dao;

import org.apache.ibatis.annotations.Param;

import com.digger.pojo.User;
import com.digger.common.ServerResponse;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	int checkUsername(String username);

	User selectLogin(@Param("username")String username,@Param("password")String password);

    
    
}
package com.digger.dao;

import java.util.List;

import com.digger.pojo.Friend;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);
    
    List<Friend> selectByPrimaryKey(Integer id);
}
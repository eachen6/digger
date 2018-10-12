package com.digger.dao;

import java.util.List;

import com.digger.pojo.Friend;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);
    
    List<Friend> selectByPrimaryKey(Integer id);
    
    /**
     * 根据用户ID获取用户好友ID列表
     */
    List<Integer> selectByUserId(Integer id);
}
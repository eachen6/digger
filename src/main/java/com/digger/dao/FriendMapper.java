package com.digger.dao;

import com.digger.pojo.Friend;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);
}
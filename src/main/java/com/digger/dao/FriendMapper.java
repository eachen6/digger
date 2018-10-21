package com.digger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.digger.pojo.Friend;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);
    
    List<Friend> selectByPrimaryKey(Integer id);
    
    /**
     * 根据用户ID获取用户好友ID列表
     */
    List<Integer> selectByUserId(Integer id);
    
    Friend selectByDoubleId(@Param("userid")Integer userid,@Param("friendid")Integer friendid);

	List<Friend> selectByFriendid(Integer userid);
	
	int updateState(Friend friend);
	
	int deleteByRecord(Friend friend);
}
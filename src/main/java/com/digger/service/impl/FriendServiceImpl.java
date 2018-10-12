package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.service.FriendService;
import com.digger.dao.FriendMapper;
import com.digger.dao.UserMapper;
import com.digger.pojo.Friend;
import com.digger.vo.CarouseVO;
import com.digger.vo.FriendVO;
import com.mysql.fabric.xmlrpc.base.Array;

@Service("friendService")
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendMapper friendMapper;
	@Autowired
	UserMapper userMapper;
	
	/* 
	 * 获取个人好友列表
	 * @author 徐子颖
	 * eachen 重写获取好友列表功能，同时获取id与name
	 */
	@Override
	public ServerResponse toGetPersonalFriendList(Integer id) {
		// TODO Auto-generated method stub
		List<FriendVO> friends = new ArrayList<FriendVO>();
		List<Friend> friendList = new ArrayList<Friend>();
		friendList = friendMapper.selectByPrimaryKey(id);
		for(Friend friend : friendList) {
			String temp = userMapper.selectNameByID(friend.getFriendint());
			FriendVO friendVO = new FriendVO();
			friendVO.setId(friend.getFriendint());
			friendVO.setName(temp);
			friends.add(friendVO);
		}
		return ServerResponse.createBySuccess(friends);
	}
	
	public ServerResponse getPersonalFriends(Integer id) {
		List<String> friendsName = new ArrayList<String>();
		//先根据id返回friends的id列表
		List<Friend> friendList = new ArrayList<Friend>();
		friendList = friendMapper.selectByPrimaryKey(id);
		for(Friend friend : friendList) {
			String temp = userMapper.selectNameByID(friend.getFriendint());
			friendsName.add(temp);
		}
		System.out.println(friendsName);
		return ServerResponse.createBySuccess(friendsName);
	}

}

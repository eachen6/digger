package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.controller.FriendService;
import com.digger.dao.FriendMapper;
import com.digger.pojo.Friend;
import com.digger.vo.CarouseVO;
import com.mysql.fabric.xmlrpc.base.Array;

@Service("friendService")
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendMapper friendMapper;
	
	@Override
	public ServerResponse toGetPersonalFriendList(Integer id) {
		// TODO Auto-generated method stub
		List<Friend> friendList = new ArrayList<Friend>();
		friendList = friendMapper.selectByPrimaryKey(id);
		return ServerResponse.createBySuccess(friendList);
	}

}

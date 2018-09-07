package com.digger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.dao.UserMapper;
import com.digger.pojo.User;
import com.digger.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	public int register(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public User todologin(String username, String password) {
		// TODO Auto-generated method stub
		return userMapper.todologin(username, password);
	}

}

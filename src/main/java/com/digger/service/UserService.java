package com.digger.service;

import org.springframework.stereotype.Service;

import com.digger.pojo.User;
import com.digger.common.ServerResponse;


public interface UserService {
	
	public int register(User user);
	
	ServerResponse<User> login(String username, String password);
	
}

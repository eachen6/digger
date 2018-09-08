package com.digger.service;


import com.digger.pojo.User;
import com.digger.common.ServerResponse;


public interface UserService {
	
	public ServerResponse<String> register(User user);
	
	ServerResponse<User> login(String username, String password);
	
	public ServerResponse<String> checkValid(String str,String type);
}

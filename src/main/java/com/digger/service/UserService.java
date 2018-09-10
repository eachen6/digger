package com.digger.service;


import com.digger.pojo.User;
import com.digger.common.ServerResponse;


public interface UserService {
	
	public ServerResponse<String> register(User user);
	
	ServerResponse<User> login(String username, String password);
	
	public ServerResponse<String> checkValid(String str,String type);

	public ServerResponse<User> selectQuestion(String username,String type);

	public ServerResponse<String> checkAnswer(String username,String question, String answer, String type);

	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken, String type);
}

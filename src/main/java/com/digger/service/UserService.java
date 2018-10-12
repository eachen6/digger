package com.digger.service;


import com.digger.pojo.User;

import java.util.List;

import com.digger.common.ServerResponse;


public interface UserService {
	
	public ServerResponse<String> register(User user);
	
	ServerResponse<User> login(String username, String password);
	
	public ServerResponse<String> checkValid(String str,String type);

	public ServerResponse<User> selectQuestion(String username,String type);

	public ServerResponse<String> checkAnswer(String username,String question, String answer, String type);

	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken, String type);

	public ServerResponse<String> updatePassword(String passwordOld, String passwordNew, User user);

	public ServerResponse<User> updateInformation(User user, User currentUser);  //更新个人信息
	
	public ServerResponse<User> getUserInfo(int userId);

	public List<User> getAllUser();
<<<<<<< HEAD
	
	public String selectNameByID(Integer id);
=======

	public ServerResponse getTotalUserList();

	public ServerResponse updateStateById(Integer id, Integer state);

	public ServerResponse deleteUserById(Integer id);

	public ServerResponse selectUserByUsername(String username);

	public ServerResponse sortById();

	public ServerResponse sortByState();

	public ServerResponse updatePassword(String username, String passwordOld, String passwordNew,
			String passwordRepeat);
>>>>>>> 370c307efe23db3507e7bfa02d7df3644caeabd2
}

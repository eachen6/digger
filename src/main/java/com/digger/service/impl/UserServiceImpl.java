package com.digger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.UserMapper;
import com.digger.pojo.User;
import com.digger.service.UserService;
import com.digger.utils.MD5Util;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	public int register(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public ServerResponse<User> login(String username, String password) {
		// TODO Auto-generated method stub
		int resultCount = userMapper.checkUsername(username);
		if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		System.out.println(md5Password);
        User user  = userMapper.selectLogin(username,md5Password);
		if(user == null){
	        return ServerResponse.createByErrorMessage("密码错误");
	    }
		user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
	}

}

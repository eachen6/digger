package com.digger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.dao.UserMapper;
import com.digger.pojo.User;
import com.digger.service.UserService;
import com.digger.utils.MD5Util;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

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
	
	public ServerResponse<String> register(User user) {
		ServerResponse validResponse = checkValid(user.getUsername(), Const.USERNAME);
		if(!validResponse.isSuccess())
		{
			return validResponse;
		}
		validResponse = checkValid(user.getEmail(), Const.EMAIL);
		if(!validResponse.isSuccess())
		{
			return validResponse;
		}
		user.setRole(Const.Role.ROLE_CUSTOMER);
		String md5Password = MD5Util.MD5EncodeUtf8(user.getPassword());
		System.out.println(md5Password);
		user.setPassword(md5Password);
		int resultCount = 0;
		resultCount = userMapper.insert(user);
		//System.out.println(resultCount+"uuuuuuuuuuuuuuuuuuuuuuuuu");
		if(resultCount>0)
		{
			return ServerResponse.createBySuccessMessage("注册成功");
		}
		return ServerResponse.createByErrorMessage("注册失败");
	}
	
	
	public ServerResponse<String> checkValid(String str,String type){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

}

package com.digger.service;

import org.springframework.stereotype.Service;

import com.digger.pojo.User;


public interface UserService {
	public int register(User user);
	public User todologin(String username,String password);
}

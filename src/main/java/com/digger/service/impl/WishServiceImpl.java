package com.digger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.WishMapper;
import com.digger.service.WishService;

@Service("wishService")
public class WishServiceImpl implements WishService{
	
	@Autowired
	WishMapper wishMapper;

	@Override
	public ServerResponse toGetWishGame(int gameid) {
		// TODO Auto-generated method stub
		int resultCount = wishMapper.selectByGameid(gameid);
		if(resultCount>0){
			return ServerResponse.createBySuccess(0);
		}
		return ServerResponse.createBySuccess(1);
	}
	
}

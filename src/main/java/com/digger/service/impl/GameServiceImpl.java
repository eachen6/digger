package com.digger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.GameMapper;
import com.digger.pojo.Game;

@Service("gameService")
public class GameServiceImpl {
	
	@Autowired
	GameMapper gameMapper;
	
	public ServerResponse toAddGame(Game game){
		
		int rowCount = gameMapper.insert(game);
		if(rowCount > 0){
            return ServerResponse.createBySuccess("上传游戏成功");
        }
        return ServerResponse.createByErrorMessage("上传游戏失败");
		
	}
}

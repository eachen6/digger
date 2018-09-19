package com.digger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.GameMapper;
import com.digger.pojo.Game;
import com.digger.pojo.Order;
import com.digger.service.GameService;
import com.digger.vo.GamelistVO;
import com.digger.vo.CarouseVO;

@Service("gameService")
public class GameServiceImpl implements GameService{
	
	@Autowired
	GameMapper gameMapper;
	
	public ServerResponse toAddGame(Game game){
		
		int rowCount = gameMapper.insert(game);
		if(rowCount > 0){
            return ServerResponse.createBySuccess("上传游戏成功");
        }
        return ServerResponse.createByErrorMessage("上传游戏失败");
		
	}

	@Override
	public ServerResponse toGetHotSaleCarouse() {
		// TODO Auto-generated method stub
		List<CarouseVO> carouseList = new ArrayList<CarouseVO>();
		carouseList = gameMapper.toGetHotSaleCarouse();
		return ServerResponse.createBySuccess(carouseList);
	}

	@Override
	public ServerResponse toGetHotSaleGameList() {
		// TODO Auto-generated method stub
		List<GamelistVO> gamelist = new ArrayList<GamelistVO>();
		gamelist = gameMapper.toGetHotSaleGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	@Override
	public ServerResponse toGetTotalGameList() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetTotalGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	@Override
	public ServerResponse toGetDiscountGameList() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetDiscountGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	@Override
	public ServerResponse toGetDetail(int id) {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetDetail(id);
		return ServerResponse.createBySuccess(gamelist);
	}

}

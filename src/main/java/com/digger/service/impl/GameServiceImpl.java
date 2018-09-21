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
	
	/* 
	 * 增加游戏
	 * @author 陈进雄
	 */
	public ServerResponse toAddGame(Game game){
		
		int rowCount = gameMapper.insert(game);
		if(rowCount > 0){
            return ServerResponse.createBySuccess("上传游戏成功");
        }
        return ServerResponse.createByErrorMessage("上传游戏失败");
		
	}

	/* 
	 * 获取热销游戏轮播图集合
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetHotSaleCarouse() {
		// TODO Auto-generated method stub
		List<CarouseVO> carouseList = new ArrayList<CarouseVO>();
		carouseList = gameMapper.toGetHotSaleCarouse();
		return ServerResponse.createBySuccess(carouseList);
	}

	/* 
	 * 获取热销游戏集合
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetHotSaleGameList() {
		// TODO Auto-generated method stub
		List<GamelistVO> gamelist = new ArrayList<GamelistVO>();
		gamelist = gameMapper.toGetHotSaleGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 获取所有游戏集合
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetTotalGameList() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetTotalGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 获取特惠游戏集合
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetDiscountGameList() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetDiscountGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 获取预告游戏轮播图集合
	 * @author 高志劲
	 */
	public ServerResponse toGetNoticeCarouse() {
		List<CarouseVO> carouseList = new ArrayList<CarouseVO>();
		carouseList = gameMapper.toGetNoticeCarouse();
		return ServerResponse.createBySuccess(carouseList);
	}

	
	/* 
	 * 获取预告游戏集合
	 * @author 高志劲
	 */
	public ServerResponse toGetNoticeGameList() {
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkk");
		List<CarouseVO> carouseList = new ArrayList<CarouseVO>();
		carouseList = gameMapper.toGetNoticeGameList();
		return ServerResponse.createBySuccess(carouseList);
	}

	/* 
	 * 获取游戏详情
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetDetail(int id) {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetDetail(id);
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 搜索框根据关键词提示
	 * @author 高志劲
	 */
	@Override
	public ServerResponse searchGameByword(String keyword) {
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.searchGamewByword(keyword);
		//System.out.println(gamelist.get(0).getName());
		return ServerResponse.createBySuccess(gamelist);
	}
}

package com.digger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.digger.common.ServerResponse;
import com.digger.pojo.Game;
import com.digger.vo.CarouseVO;

public interface GameService {

	ServerResponse toAddGamefile(Map map,int id);

	ServerResponse toGetHotSaleCarouse();

	ServerResponse toGetHotSaleGameList();

	ServerResponse toGetDiscountGameList();

	ServerResponse toGetDetail(int id);

	ServerResponse toGetNoticeCarouse();
	
	ServerResponse toGetNoticeGameList();

	ServerResponse searchGameByword(String keyword);

	List<CarouseVO> searchGameByname(String name);

	ServerResponse toGetTotalGame();

	ServerResponse unauditedGamelistByName(String name);

	ServerResponse notOnTheShelfGamelistByName(String name);

	ServerResponse onTheShelfGamelistByName(String name);

	ServerResponse pullOffShelvesGamelistByName(String name);

	ServerResponse unauditedGamelist();

	ServerResponse notOnTheShelfGamelist();

	ServerResponse onTheShelfGamelist();

	ServerResponse pullOffShelvesGamelist();

	ServerResponse onThesShelfGame(Integer id);

	ServerResponse pullOffShelvesGame(Integer id);
	
	List<CarouseVO> toGetHotnewGameList();

	List<CarouseVO> toGetWeekhotGameList();

	List<CarouseVO> toGetMydiscountGameList();

	List<CarouseVO> toGetNewputGameList();

	void addclick(int gameid);

	public ServerResponse addGameDetails(Game game);
}

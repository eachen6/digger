package com.digger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import com.digger.common.ServerResponse;
import com.digger.pojo.Discountlist;
import com.digger.pojo.Game;
import com.digger.vo.CarouseVO;
import com.digger.vo.GameAuditVO;

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

	List<GameAuditVO> unauditedGamelistByName(String name);

	List<GameAuditVO> notOnTheShelfGamelistByName(String name);

	List<GameAuditVO> onTheShelfGamelistByName(String name);

	List<GameAuditVO> pullOffShelvesGamelistByName(String name);

	List<GameAuditVO> unauditedGamelist();

	List<GameAuditVO> notOnTheShelfGamelist();

	List<GameAuditVO> onTheShelfGamelist();

	List<GameAuditVO> pullOffShelvesGamelist();

	ServerResponse onThesShelfGame(Integer id);

	ServerResponse pullOffShelvesGame(Integer id);
	
	List<CarouseVO> toGetHotnewGameList();

	List<CarouseVO> toGetWeekhotGameList();

	List<CarouseVO> toGetMydiscountGameList();

	List<CarouseVO> toGetNewputGameList();

	void addclick(int gameid);

	public ServerResponse addGameDetails(Game game);

	ServerResponse auditGame(Integer id);

	ServerResponse toDeleteGame(Integer id);

	ServerResponse getNotDiscount();

	ServerResponse getDiscount();

	ServerResponse add_Discount(Discountlist dl);

	ServerResponse update_Discount(Discountlist dl);

	ServerResponse delete_Discount(Integer id);
	
	ServerResponse  download(Integer myID,Integer gameid,HttpServletResponse response);
}

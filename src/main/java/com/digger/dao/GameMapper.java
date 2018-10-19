package com.digger.dao;

import java.util.List;

import com.digger.pojo.Category;
import com.digger.pojo.Game;
import com.digger.vo.GamelistVO;
import com.digger.vo.CarouseVO;
import com.digger.vo.GameAuditVO;
import com.digger.vo.GamedetailsVO;

public interface GameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Game game);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKeyWithBLOBs(Game record);

    int updateByPrimaryKey(Game record);

	List<CarouseVO> toGetHotSaleCarouse();

	List<GamelistVO> toGetHotSaleGameList();

	List<CarouseVO> toGetHotnewGameList();

	List<CarouseVO> toGetDiscountGameList();

	List<CarouseVO> toGetNoticeCarouse();

	List<CarouseVO> toGetNoticeGameList();

	List<GamedetailsVO> toGetDetail(int id);

	List<Game> searchGamewByword(String keyword);

	List<CarouseVO> searchGamewByname(String name);

	int toGetTotalGame();

	List<GameAuditVO> unauditedGamelistByName(String name);

	List<GameAuditVO> notOnTheShelfGamelistByName(String name);

	List<GameAuditVO> onTheShelfGamelistByName(String name);

	List<GameAuditVO> pullOffShelvesGamelistByName(String name);

	List<GameAuditVO> unauditedGamelist();

	List<GameAuditVO> notOnTheShelfGamelist();

	List<GameAuditVO> onTheShelfGamelist();

	List<GameAuditVO> pullOffShelvesGamelist();

	int onTheShelfGame(Integer id);

	int pullOffShelvesGame(Integer id);

	String findCoverimg(Integer gameid);

	List<CarouseVO> toGetWeekhotGameList();

	List<CarouseVO> toGetNewputGameList();

	List<CarouseVO> toGetMydiscountGameList();

	int addclick(int gameid);

	int auditGame(Integer id);

}
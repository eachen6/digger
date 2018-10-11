package com.digger.dao;

import java.util.List;

import com.digger.pojo.Game;
import com.digger.vo.GamelistVO;
import com.digger.vo.CarouseVO;

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

	List<Game> toGetTotalGameList();

	List<CarouseVO> toGetDiscountGameList();

	List<CarouseVO> toGetNoticeCarouse();

	List<CarouseVO> toGetNoticeGameList();

	List<Game> toGetDetail(int id);

	List<Game> searchGamewByword(String keyword);

	List<CarouseVO> searchGamewByname(String name);

	int toGetTotalGame();

}
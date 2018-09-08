package com.digger.dao;

import com.digger.pojo.Game;

public interface GameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Game game);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKeyWithBLOBs(Game record);

    int updateByPrimaryKey(Game record);

}
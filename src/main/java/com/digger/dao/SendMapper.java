package com.digger.dao;

import org.apache.ibatis.annotations.Param;

import com.digger.pojo.Send;

public interface SendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("send")Send send);

    int insertSelective(Send record);

    Send selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Send record);

    int updateByPrimaryKeyWithBLOBs(Send record);

    int updateByPrimaryKey(Send record);

	String findtargetid(String ordernum);

	Integer isbuyforit(@Param("ordernum")String ordernum, @Param("gameid")Integer gameid, @Param("targetid")int targetid, @Param("id")Integer id);
}
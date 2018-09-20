package com.digger.dao;

import com.digger.pojo.Send;

public interface SendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Send send);

    int insertSelective(Send record);

    Send selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Send record);

    int updateByPrimaryKeyWithBLOBs(Send record);

    int updateByPrimaryKey(Send record);
}
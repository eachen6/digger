package com.digger.dao;

import com.digger.pojo.Discountlist;

public interface DiscountlistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Discountlist record);

    int insertSelective(Discountlist record);

    Discountlist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Discountlist record);

    int updateByPrimaryKey(Discountlist record);
}
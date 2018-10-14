package com.digger.dao;

import java.util.List;

import com.digger.pojo.Order;
import com.digger.vo.OrderVO;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order order);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	List<OrderVO> toGetOrder(Integer userid);
}
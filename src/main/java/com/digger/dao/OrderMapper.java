package com.digger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.digger.pojo.Order;
import com.digger.pojo.Payinfo;
import com.digger.vo.FindMyGameVO;
import com.digger.vo.OrderVO;

public interface OrderMapper {
    Integer deleteByid(Integer id);

    int insert(Order order);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	List<OrderVO> toGetOrder(Integer userid);

	Integer isBuy_Order(@Param("userid")int userid,@Param("gameid")int gameid);

	Order getOrderByOrdernum(String ordernum);

	Integer updateOrderStatus(@Param("paid")int paid, @Param("ordernum")Long ordernum);

	int insertPayinfo(@Param("pay")Payinfo pay);

	Integer updateOldOrderstatus(String ordernum);

	Integer cancelByid(Integer id);

	Integer applyRefundByid(Integer id);

	List<FindMyGameVO> toGetMyGame(Integer userid);

}
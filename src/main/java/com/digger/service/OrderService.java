package com.digger.service;



import com.digger.common.ServerResponse;
import com.digger.pojo.Order;

public interface OrderService {

	ServerResponse toCreateOrder(Order order);

	ServerResponse toGetOrder(Integer userid);

	ServerResponse toDeleteOrder(Integer id);

	ServerResponse isBuy_Order(int id, int userid);

	Order getOrderByOrdernum(String ordernum);

	void updateOrderStatus(String out_trade_no, String trade_no, String total_amount);


}

package com.digger.service;



import java.util.List;

import com.digger.common.ServerResponse;
import com.digger.pojo.Order;
import com.digger.vo.OrderVO;

public interface OrderService {

	ServerResponse toCreateOrder(Order order);

	ServerResponse toGetOrder(Integer userid);

	ServerResponse toDeleteOrder(Integer id);

	ServerResponse isBuy_Order(int id, int userid);

	Order getOrderByOrdernum(String ordernum);

	void updateOrderStatus(String out_trade_no, String trade_no, String total_amount);

	ServerResponse toCancelOrder(Integer id);

	ServerResponse toApplyRefund(Integer id);

	List<OrderVO> toGetRefund();

	boolean goRefund(String ordernum);

	ServerResponse disagreeRefund(String ordernum);


}

package com.digger.service;



import com.digger.common.ServerResponse;

public interface OrderService {

	ServerResponse<String> toCreateOrder(Integer userid, int gameid, float price);

	ServerResponse toGetOrder(Integer userid);

	ServerResponse toDeleteOrder(Integer id);

	ServerResponse isBuy_Order(int id, int userid);

}

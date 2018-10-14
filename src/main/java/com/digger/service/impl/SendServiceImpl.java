package com.digger.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.GameMapper;
import com.digger.dao.OrderMapper;
import com.digger.dao.SendMapper;
import com.digger.pojo.Game;
import com.digger.pojo.Order;
import com.digger.pojo.Send;
import com.digger.service.SendService;

@Service("sendService")
public class SendServiceImpl implements SendService {

	@Autowired
	SendMapper sendMapper;
	
	@Autowired
	GameMapper gameMapper;
	
	@Autowired
	OrderMapper orderMapper;

	/* 
	 * 生成赠送记录
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse<String> toSendGame(Integer gameid, Integer id, int targetid, float price, String message) {
		// TODO Auto-generated method stub

		//生成赠送记录
		Send send = new Send();
		send.setTargetid(targetid);
		send.setMessage(message);
		// 获取流水号
		long orderNum = 0;
		int r1 = (int) (Math.random() * (10));// 产生2个0-9的随机数
		int r2 = (int) (Math.random() * (10));
		long timestamp = System.currentTimeMillis();// 一个13位的时间戳
		String timeStamp = String.valueOf(r1) + String.valueOf(r2) + String.valueOf(timestamp);// 订单ID
		orderNum = Long.parseLong(timeStamp);
		send.setOrdernum(orderNum);
		
		int resultCount = 0;
		resultCount = sendMapper.insert(send);
		if(resultCount>0){
			
			//获取游戏信息
			String coverimg = null;
			coverimg = gameMapper.findCoverimg(gameid);
			Order order = new Order();
			order.setGameid(gameid);
			order.setUserid(id);
			order.setCoverimg(coverimg);
			order.setState((byte) 0);
			order.setPrice(price);
			order.setIssend((byte) 1);
			order.setPaytime(null);
			order.setOrdernum(orderNum);
			//获取关闭时间（例如：五分钟）
			Date now = new Date();
			Date time = new Date(now.getTime() + 300000);
			order.setClosetime(time);
			
			int result = 0;
			result = orderMapper.insert(order);
			if(result>0){
				return ServerResponse.createBySuccessMessage("已生成赠送订单，请购买");
			}
			return ServerResponse.createByErrorMessage("赠送订单生成失败");
		}
		
		return ServerResponse.createByErrorMessage("赠送订单生成失败");
	}

}

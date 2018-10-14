package com.digger.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.GameMapper;
import com.digger.dao.OrderMapper;
import com.digger.pojo.Game;
import com.digger.pojo.Order;
import com.digger.service.OrderService;
import com.digger.vo.OrderVO;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	GameMapper gameMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	/* 
	 * 生成订单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse<String> toCreateOrder(Integer userid, int gameid, float price)  {
		// TODO Auto-generated method stub
		
		//获取游戏信息
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetDetail(gameid);
		String coverimg = null;
		long orderNum = 0;
		for(Game list : gamelist){
			coverimg = list.getCoverurl();
		}
		
		Order order = new Order();
		order.setGameid(gameid);
		order.setUserid(userid);
		order.setCoverimg(coverimg);
		order.setState((byte) 0);
		order.setPrice(price);
		order.setIssend((byte) 0);
		order.setPaytime(null);
		//获取流水号
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long timestamp = System.currentTimeMillis();//一个13位的时间戳
		String timeStamp =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(timestamp);// 订单ID
		orderNum = Long.parseLong(timeStamp);
		order.setOrdernum(orderNum);
		//获取关闭时间（例如：五分钟）
		Date now = new Date();
		Date time = new Date(now.getTime() + 300000);
		order.setClosetime(time);
		
		int resultCount = 0;
		resultCount = orderMapper.insert(order);
		if(resultCount>0){
			return ServerResponse.createBySuccessMessage("已生成订单，请购买");
		}
		
		return ServerResponse.createByErrorMessage("订单生成失败");
	}

	/* 
	 * 查看订单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetOrder(Integer userid) {
		// TODO Auto-generated method stub
		List<OrderVO> list = new ArrayList<OrderVO>();
		list = orderMapper.toGetOrder(userid);
		if(CollectionUtils.isEmpty(list)){
			return ServerResponse.createByErrorMessage("查看订单失败");
		}
		return ServerResponse.createBySuccess("查看订单成功", list);
		
	}

	/* 
	 * 删除订单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toDeleteOrder(Integer id) {
		// TODO Auto-generated method stub
		int rowCount = orderMapper.deleteByPrimaryKey(id);
		System.out.println(rowCount);
		if(rowCount>0){
            return ServerResponse.createBySuccessMessage("删除成功！");
        }
        return ServerResponse.createByErrorMessage("删除失败！");
	}
	
}

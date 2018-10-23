package com.digger.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.dao.GameMapper;
import com.digger.dao.OrderMapper;
import com.digger.dao.SendMapper;
import com.digger.dao.WishMapper;
import com.digger.pojo.Game;
import com.digger.pojo.Order;
import com.digger.pojo.Payinfo;
import com.digger.service.OrderService;
import com.digger.vo.FindMyGameVO;
import com.digger.vo.OrderVO;
import com.digger.vo.StatisticsVO;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	GameMapper gameMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	WishMapper wishMapper;
	
	@Autowired
	SendMapper sendMapper;
	
	/* 
	 * 生成订单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toCreateOrder(Order order)  {
		// TODO Auto-generated method stub
		
		//获取游戏信息
		String coverimg = null;
		coverimg = gameMapper.findCoverimg(order.getGameid());
		order.setCoverimg(coverimg);
		order.setState((byte) 0);
		order.setPaytime(null);
		//获取流水号
		/*int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long timestamp = System.currentTimeMillis();//一个13位的时间戳
		String timeStamp =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(timestamp);// 订单ID
		orderNum = Long.parseLong(timeStamp);
		order.setOrdernum(orderNum);*/
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
			return ServerResponse.createByErrorCodeMessage(1,"您还没有订单，快去下订单吧");
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
		Integer rowCount = 0;
		rowCount = orderMapper.deleteByid(id);
		System.out.println(rowCount);
		if(rowCount>0){
            return ServerResponse.createBySuccessMessage("删除成功！");
        }
        return ServerResponse.createByErrorMessage("删除失败！");
	}
	
	
	/* 
	 * 取消订单退款
	 * @author 高志劲
	 */
	@Override
	public ServerResponse toCancelOrder(Integer id) {
		Integer rowCount = 0;
		rowCount = orderMapper.cancelByid(id);
		if(rowCount>0){
            return ServerResponse.createBySuccessMessage("取消成功！");
        }
        return ServerResponse.createByErrorMessage("取消失败！");
	}
	
	
	/* 
	 * 申请退款
	 * @author 高志劲
	 */
	@Override
	public ServerResponse toApplyRefund(Integer id) {
		Integer rowCount = 0;
		rowCount = orderMapper.applyRefundByid(id);
		if(rowCount>0){
            return ServerResponse.createBySuccessMessage("已申请退款！");
        }
        return ServerResponse.createByErrorMessage("申请退款失败！");
	}

	/* 
	 * 查询该游戏是否已被用户购买
	 * @author 高志劲
	 */
	@Override
	public ServerResponse isBuy_Order(int gameid,int userid) {
		Integer resultCount = 0;
		System.out.println(gameid+"    "+userid);		
		resultCount = orderMapper.isBuy_Order(userid, gameid);  //0代表别人赠送过改款游戏给我,1则反之
		System.out.println(resultCount);
		if(resultCount > 0 ){
            return ServerResponse.createBySuccessMessage("您已购买该游戏！");
        }
        return ServerResponse.createByErrorMessage("您未购买该游戏！");
	}

	/* 
	 * 根据ordernum获取订单详情
	 * @author 高志劲
	 */
	@Override
	public Order getOrderByOrdernum(String ordernum) {
		Order order = orderMapper.getOrderByOrdernum(ordernum);
		return order;
	}

	/* 
	 * 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
	 * @author 高志劲
	 */
	@Override
	public void updateOrderStatus(String out_trade_no, String trade_no, String total_amount) {
		Order order = orderMapper.getOrderByOrdernum(out_trade_no);
		if (order.getState()==Const.WAIT_PAY) {
			String targetid = null;
			targetid = sendMapper.findtargetid(out_trade_no);
			Integer result1 = 0;
			if(targetid == null){
			     result1 = wishMapper.deleteWish(out_trade_no);
			}
			else{
				 result1 = wishMapper.deleteWish1(out_trade_no,targetid);
			}
			Integer result2 = 0;
			result2 = orderMapper.updateOldOrderstatus(out_trade_no);
			Integer result = 0;
			result = orderMapper.updateOrderStatus(Const.PAID,order.getOrdernum());
			if(result>0){
			Payinfo pay = new Payinfo();
			pay.setOrdernum(order.getOrdernum());
			pay.setPayplatform(Const.PAYWAY_ALI);
			pay.setPlatformnumber(trade_no);
			pay.setUserid(order.getUserid());
			result = orderMapper.insertPayinfo(pay);
		    }
	    }
	}

	/* 
	 * 查看我的游戏包含别人赠送的
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetMyGame(Integer userid) {
		// TODO Auto-generated method stub
		List<FindMyGameVO> list = new ArrayList<FindMyGameVO>();
		list = orderMapper.toGetMyGame(userid);
		if(CollectionUtils.isEmpty(list)){
			return ServerResponse.createByErrorCodeMessage(1,"未拥有任何游戏！");
		}
		return ServerResponse.createBySuccess("成功查看我的游戏", list);
	}

	/* 
	 * 销量统计
	 * @author 高志劲
	 */
	@Override
	public ServerResponse saleStatistics(String year) {
		List<StatisticsVO> list = new ArrayList<StatisticsVO>();
		list = orderMapper.saleStatistics(year);
		return ServerResponse.createBySuccess(list);
	}
	
	/* 
	 * 各游戏销量统计
	 * @author 高志劲
	 */
	@Override
	public ServerResponse gameSaleStatistics(String date1, String date2) {
		List<StatisticsVO> list = new ArrayList<StatisticsVO>();
		System.out.println(date2);
		list = orderMapper.gameSaleStatistics(date1, date2);
		return ServerResponse.createBySuccess(list);
	}	
	
	/* 
	 * 获取退款列表
	 * @author 高志劲
	 * 查看我的游戏包含别人赠送的
	 * @author 徐子颖
	 */
	@Override
	public List<OrderVO> toGetRefund() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		list = orderMapper.toGetRefund();
		return list;
	}

	/* 
	 * 同意退款（将state该为2）
	 * @author 高志劲
	 */
	@Override
	public boolean goRefund(String ordernum) {
		Integer result = 0;
		result = orderMapper.goRefund(ordernum);
		System.out.println(result+"--------------------------------");
		if(result>0)
			return true;
		else 
			return false;
	}

	/* 
	 * 不同意退款（将state该为4）
	 * @author 高志劲
	 */
	@Override
	public ServerResponse disagreeRefund(String ordernum) {
		Integer result = 0;
		System.out.println(ordernum+"kkkkkkkkkkkkkkkk");
		result = orderMapper.disagreeRefund(ordernum);
		if(result>0)
			return ServerResponse.createBySuccessMessage("已不同意退款！");
		else 
			return ServerResponse.createByErrorMessage("不同意退款失败！");
	}


}

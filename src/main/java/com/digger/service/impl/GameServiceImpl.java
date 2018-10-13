package com.digger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.GameMapper;
import com.digger.pojo.Category;
import com.digger.pojo.Game;
import com.digger.pojo.Order;
import com.digger.service.GameService;
import com.digger.vo.GamelistVO;
import com.digger.vo.CarouseVO;

@Service("gameService")
public class GameServiceImpl implements GameService{
	
	@Autowired
	GameMapper gameMapper;
	
	/* 
	 * 增加游戏
	 * @author 陈进雄
	 */
	public ServerResponse toAddGame(Game game){
		
		int rowCount = gameMapper.insert(game);
		if(rowCount > 0){
            return ServerResponse.createBySuccess("上传游戏成功");
        }
        return ServerResponse.createByErrorMessage("上传游戏失败");
		
	}

	/* 
	 * 获取热销游戏轮播图集合
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetHotSaleCarouse() {
		// TODO Auto-generated method stub
		List<CarouseVO> carouseList = new ArrayList<CarouseVO>();
		carouseList = gameMapper.toGetHotSaleCarouse();
		return ServerResponse.createBySuccess(carouseList);
	}

	/* 
	 * 获取热销游戏集合
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetHotSaleGameList() {
		// TODO Auto-generated method stub
		List<GamelistVO> gamelist = new ArrayList<GamelistVO>();
		gamelist = gameMapper.toGetHotSaleGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 获取特惠游戏集合
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetDiscountGameList() {
		// TODO Auto-generated method stub
		List<CarouseVO> gamelist = new ArrayList<CarouseVO>();
		gamelist = gameMapper.toGetDiscountGameList();
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 获取预告游戏轮播图集合
	 * @author 高志劲
	 */
	public ServerResponse toGetNoticeCarouse() {
		List<CarouseVO> carouseList = new ArrayList<CarouseVO>();
		carouseList = gameMapper.toGetNoticeCarouse();
		return ServerResponse.createBySuccess(carouseList);
	}

	
	/* 
	 * 获取预告游戏集合
	 * @author 高志劲
	 */
	public ServerResponse toGetNoticeGameList() {
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkk");
		List<CarouseVO> carouseList = new ArrayList<CarouseVO>();
		carouseList = gameMapper.toGetNoticeGameList();
		return ServerResponse.createBySuccess(carouseList);
	}

	/* 
	 * 获取游戏详情
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetDetail(int id) {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.toGetDetail(id);
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 搜索框根据关键词提示
	 * @author 高志劲
	 */
	@Override
	public ServerResponse searchGameByword(String keyword) {
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.searchGamewByword(keyword);
		//System.out.println(gamelist.get(0).getName());
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 根据关键词搜索游戏
	 * @author 高志劲
	 */
	@Override
	public ServerResponse searchGameByname(String name) {
		List<CarouseVO> list = new ArrayList<CarouseVO>();
		list = gameMapper.searchGamewByname(name);
		return ServerResponse.createBySuccess(list);
	}

	/* 
	 * 获取游戏总数
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetTotalGame() {
		// TODO Auto-generated method stub
		int count = gameMapper.toGetTotalGame();
		return ServerResponse.createBySuccess(count);
	}

	/* 
	 * 根据游戏名查找待审核的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse unauditedGamelistByName(String name) {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.unauditedGamelistByName(name);
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
		
	}

	/* 
	 * 根据游戏名查找未上架的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse notOnTheShelfGamelistByName(String name) {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.notOnTheShelfGamelistByName(name);
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 根据游戏名查找已上架的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse onTheShelfGamelistByName(String name) {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.onTheShelfGamelistByName(name);
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 根据游戏名查找下架的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse pullOffShelvesGamelistByName(String name) {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.pullOffShelvesGamelistByName(name);
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 查找待审核的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse unauditedGamelist() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.unauditedGamelist();
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 查找未上架的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse notOnTheShelfGamelist() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.notOnTheShelfGamelist();
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 查找已上架的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse onTheShelfGamelist() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.onTheShelfGamelist();
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 查找下架的游戏列表
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse pullOffShelvesGamelist() {
		// TODO Auto-generated method stub
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameMapper.pullOffShelvesGamelist();
		if(CollectionUtils.isEmpty(gamelist)){
			return ServerResponse.createByErrorMessage("未找到该相关游戏！");
		}
		return ServerResponse.createBySuccess(gamelist);
	}

	/* 
	 * 根据id上架游戏
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse onThesShelfGame(Integer id) {
		// TODO Auto-generated method stub
		int updateCount = gameMapper.onTheShelfGame(id);
		if(updateCount>0){
			return ServerResponse.createBySuccessMessage("上架成功");
		}
		return ServerResponse.createByErrorMessage("上架失败");
	}

	/* 
	 * 根据id下架游戏
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse pullOffShelvesGame(Integer id) {
		// TODO Auto-generated method stub
		int updateCount = gameMapper.pullOffShelvesGame(id);
		if(updateCount>0){
			return ServerResponse.createBySuccessMessage("下架成功");
		}
		return ServerResponse.createByErrorMessage("下架失败");
	}
	
	/* 
	 * 获取所有游戏<火爆新品>集合
	 * @author 徐子颖
	 */
	@Override
	public List<CarouseVO> toGetHotnewGameList() {
		// TODO Auto-generated method stub
		List<CarouseVO> gamelist = new ArrayList<CarouseVO>();
		gamelist = gameMapper.toGetHotnewGameList();
		return gamelist;
	}

	/* 
	 * 获取所有游戏<本周热门>集合
	 * @author 高志劲
	 */
	@Override
	public List<CarouseVO> toGetWeekhotGameList() {
		// TODO Auto-generated method stub
		List<CarouseVO> gamelist = new ArrayList<CarouseVO>();
		gamelist = gameMapper.toGetWeekhotGameList();
		return gamelist;
	}

	/* 
	 * 获取所有游戏<最新上架>集合
	 * @author 高志劲
	 */
	@Override
	public List<CarouseVO> toGetNewputGameList() {
		List<CarouseVO> gamelist = new ArrayList<CarouseVO>();
		gamelist = gameMapper.toGetNewputGameList();
		return gamelist;
	}

	/* 
	 * 获取所有游戏<折扣促销>集合
	 * @author 高志劲
	 */
	@Override
	public List<CarouseVO> toGetMydiscountGameList() {
		List<CarouseVO> gamelist = new ArrayList<CarouseVO>();
		gamelist = gameMapper.toGetMydiscountGameList();
		return gamelist;
	}

}

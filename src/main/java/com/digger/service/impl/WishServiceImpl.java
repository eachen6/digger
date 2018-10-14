package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.GameMapper;
import com.digger.dao.WishMapper;
import com.digger.pojo.Wish;
import com.digger.service.WishService;
import com.digger.vo.WishVO;
import com.mysql.fabric.xmlrpc.base.Array;

@Service("wishService")
public class WishServiceImpl implements WishService{
	
	@Autowired
	WishMapper wishMapper;
	
	@Autowired
	GameMapper gameMapper;

	/* 
	 * 获取该游戏是否被当前用户加入愿望清单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetWishGame(Integer gameid, Integer userid) {
		// TODO Auto-generated method stub
		Integer resultCount = wishMapper.select(gameid,userid);
		if(resultCount > 0){
            return ServerResponse.createBySuccessMessage("愿望清单存在该游戏！");
        }
        return ServerResponse.createByErrorMessage("愿望清单不存在该游戏！");
	}

	/* 
	 * 将游戏添加到愿望清单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toAddWishGame(Integer gameid, Integer userid) {
		// TODO Auto-generated method stub
		Wish wish = new Wish();
		wish.setGameid(gameid);
		wish.setUserid(userid);
		
		//根据游戏id查找封面图
		String coverimg = gameMapper.findCoverimg(gameid);
		wish.setCoverimg(coverimg);
		
		int rowCount = wishMapper.insert(wish);
		if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("成功加入愿望清单！");
        }
        return ServerResponse.createByErrorMessage("未能加入愿望清单！");
	}
	
	/* 
	 * 根据游戏id和用户id将游戏从愿望清单删除
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toDeleteWishGame(Integer gameid, Integer userid) {
		// TODO Auto-generated method stub
		int rowCount = wishMapper.delete(gameid,userid);
		if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("删除成功！");
        }
        return ServerResponse.createByErrorMessage("删除失败！");
	}

	/* 
	 * 查看自己的愿望清单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetMyselfWishGame(Integer userid) {
		// TODO Auto-generated method stub
		List<WishVO> list = new ArrayList<WishVO>();
		list = wishMapper.toGetMyselfWishGame(userid);
		if(CollectionUtils.isEmpty(list)){
			return ServerResponse.createByErrorMessage("查询失败！");
        }
		return ServerResponse.createBySuccess("查询成功！", list);
        
	}

	/* 
	 * 查看好友的愿望清单
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toGetFriendWishGame(Integer userid) {
		// TODO Auto-generated method stub
		List<WishVO> list = new ArrayList<WishVO>();
		list = wishMapper.toGetMyselfWishGame(userid);
		if(CollectionUtils.isEmpty(list)){
			return ServerResponse.createByErrorMessage("查询失败！");
        }
		return ServerResponse.createBySuccess("查询成功！", list);
	}
	
}

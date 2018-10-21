package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digger.common.ServerResponse;
import com.digger.service.FriendService;
import com.digger.dao.FriendMapper;
import com.digger.dao.UserMapper;
import com.digger.pojo.Friend;
import com.digger.pojo.User;
import com.digger.vo.CarouseVO;
import com.digger.vo.FriendVO;
import com.digger.vo.MyInvite;
import com.mysql.fabric.xmlrpc.base.Array;

@Service("friendService")
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendMapper friendMapper;
	@Autowired
	UserMapper userMapper;
	
	/* 
	 * 获取个人好友列表
	 * @author 徐子颖
	 * eachen 重写获取好友列表功能，同时获取id与name
	 */
	@Override
	public ServerResponse toGetPersonalFriendList(Integer id) {
		// TODO Auto-generated method stub
		List<FriendVO> friends = new ArrayList<FriendVO>();
		List<Friend> friendList = new ArrayList<Friend>();
		friendList = friendMapper.selectByPrimaryKey(id);
		for(Friend friend : friendList) {
			String temp = userMapper.selectNameByID(friend.getFriendint());
			FriendVO friendVO = new FriendVO();
			friendVO.setId(friend.getFriendint());
			friendVO.setState(1);
			friendVO.setName(temp);
			friends.add(friendVO);
		}
		return ServerResponse.createBySuccess(friends);
	}
	
	public ServerResponse getPersonalFriends(Integer id) {
		List<String> friendsName = new ArrayList<String>();
		//先根据id返回friends的id列表
		List<Friend> friendList = new ArrayList<Friend>();
		friendList = friendMapper.selectByPrimaryKey(id);
		for(Friend friend : friendList) {
			String temp = userMapper.selectNameByID(friend.getFriendint());
			friendsName.add(temp);
		}
		System.out.println(friendsName);
		return ServerResponse.createBySuccess(friendsName);
	}
	
	public ServerResponse  make_friendship(Integer myid, String friendsname) {
		//先去查是否存在这个账号的用户
		User user = userMapper.selectUserByUsername(friendsname);
		if(user == null) {
			return ServerResponse.createByErrorMessage("该账号用户不存在，请确认后再试");
		}
		else {
			int friendsID = user.getId();
			//判断是否已经申请过添加为好友了，避免重复添加
			Friend exitFrined = friendMapper.selectByDoubleId(myid, friendsID);
			if(exitFrined != null) {
				if(exitFrined.getState() == 0)
					return ServerResponse.createBySuccessMessage("已发送好友请求，等待对方确认");
				else if(exitFrined.getState() == 1)
					return ServerResponse.createBySuccessMessage("你们已经是好友关系了，不用重复添加");
			}
			Friend friend = new Friend();
			friend.setUserid(myid);
			friend.setFriendint(friendsID);
			friend.setState((byte) 0);
			int rowCount = friendMapper.insert(friend);
			if(rowCount > 0) {
				return ServerResponse.createBySuccessMessage("已发送好友请求，等待对方确认");
			}
		}
		return ServerResponse.createByErrorMessage("添加好友失败");
	}
	
	public ServerResponse  get_Friend_invite(Integer userid) {
		//获取自己被申请为好友的请求列表
		List<MyInvite>myInvites = new ArrayList<MyInvite>();
		List<Friend>friends = new ArrayList<Friend>();
		friends = friendMapper.selectByFriendid(userid);
		if(friends.size()>0) {
			for(Friend friend:friends) {
				String friendsName = userMapper.selectNameByID(friend.getUserid());
				MyInvite myInvite = new MyInvite();
				myInvite.setInviteUserid(friend.getUserid());
				myInvite.setInviteUsername(friendsName);
				myInvite.setState(friend.getState());
				myInvites.add(myInvite);
			}
			return ServerResponse.createBySuccess("待通过的好友申请有", myInvites);
		}
		return ServerResponse.createByErrorMessage("未获取到好友信息");
	}
	
	@Transactional()
	public ServerResponse  pass_Friend_invite(Integer myID,Integer friendID) {
		Friend friend = new Friend();
		friend.setUserid(friendID);
		friend.setFriendint(myID);
		int rowCount = friendMapper.updateState(friend);
		if(rowCount > 0) {
			Friend friend2 = new Friend();
			friend2.setUserid(myID);
			friend2.setFriendint(friendID);
			friend2.setState((byte) 1);
			int rowCount2 = friendMapper.insert(friend2);
			if(rowCount2 > 0)
				return ServerResponse.createBySuccessMessage("已通过好友申请，建立好友关系");
		}
		else {
			//事务回滚
			return ServerResponse.createBySuccessMessage("未通过好友申请，请稍后重试");
		}
		return ServerResponse.createByErrorMessage("未通过申请，建立好友关系失败");
	}
	
	public ServerResponse  deleteUnSureInvite(Integer MyID,Integer friendID) {
		Friend friend = new Friend();
		friend.setUserid(friendID);
		friend.setFriendint(MyID);
		int rowCount = friendMapper.deleteByRecord(friend);
		if(rowCount > 0)
			return ServerResponse.createBySuccessMessage("删除成功");
		return ServerResponse.createByErrorMessage("删除失败");
	}

}

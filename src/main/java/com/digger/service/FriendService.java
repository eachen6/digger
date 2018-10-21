package com.digger.service;

import java.util.List;

import com.digger.common.ServerResponse;

public interface FriendService {

	ServerResponse toGetPersonalFriendList(Integer id);

	ServerResponse getPersonalFriends(Integer id);
	
	ServerResponse  make_friendship(Integer myid, String friendsname);
	
	ServerResponse  get_Friend_invite(Integer userid);
	
	ServerResponse  pass_Friend_invite(Integer myID,Integer friendID);
	
	ServerResponse  deleteUnSureInvite(Integer MyID,Integer friendID);
}

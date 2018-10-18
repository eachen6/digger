package com.digger.service;

import com.digger.common.ServerResponse;

public interface WishService {

	ServerResponse toGetWishGame(Integer gameid, Integer userid);

	ServerResponse toAddWishGame(Integer gameid, Integer userid);

	ServerResponse toGetMyselfWishGame(Integer userid);

	ServerResponse toGetFriendWishGame(Integer userid);

	ServerResponse toDeleteWishGame(int gameid, Integer userid);
	
}

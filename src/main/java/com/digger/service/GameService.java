package com.digger.service;

import com.digger.common.ServerResponse;
import com.digger.pojo.Game;

public interface GameService {

	ServerResponse toAddGame(Game game);

	ServerResponse toGetHotSaleCarouse();

	ServerResponse toGetHotSaleGameList();
	
}

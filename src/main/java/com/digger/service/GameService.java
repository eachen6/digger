package com.digger.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.digger.common.ServerResponse;
import com.digger.pojo.Game;

public interface GameService {

	ServerResponse toAddGame(Game game);

	ServerResponse toGetHotSaleCarouse();

	ServerResponse toGetHotSaleGameList();

	ServerResponse toGetTotalGameList();

	ServerResponse toGetDiscountGameList();

	ServerResponse toGetDetail(int id);

	
}

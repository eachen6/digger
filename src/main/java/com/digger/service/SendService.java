package com.digger.service;

import com.digger.common.ServerResponse;

public interface SendService {

	ServerResponse<String> toSendGame(Integer gameid, Integer id, int targetid, float price, String message,String ordernum);

}

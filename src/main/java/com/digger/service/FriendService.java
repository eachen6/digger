package com.digger.service;

import java.util.List;

import com.digger.common.ServerResponse;

public interface FriendService {

	ServerResponse toGetPersonalFriendList(Integer id);

	ServerResponse getPersonalFriends(Integer id);
}

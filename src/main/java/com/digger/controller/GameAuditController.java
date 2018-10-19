package com.digger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.ServerResponse;
import com.digger.service.GameService;

@Controller
@RequestMapping("/gameaudit")
public class GameAuditController {

	@Autowired
	GameService gameService;
	
	/**
     * 根据游戏名获取待审核的游戏列表
     * @return
     */
	@RequestMapping(value = "unaudited_gamelistbyname", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse unauditedGamelistByName(String name)
	{
		return gameService.unauditedGamelistByName(name);
	}
	
	/**
     * 根据游戏名获取未上架的游戏列表
     * @return
     */
	@RequestMapping(value = "notonthesshelf_gamelistbyname", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse notOnTheShelfGamelistByName(String name)
	{
		return gameService.notOnTheShelfGamelistByName(name);
	}
	
	/**
     * 根据游戏名获取已上架的游戏列表
     * @return
     */
	@RequestMapping(value = "onthesshelf_gamelistbyname", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse onTheShelfGamelistByName(String name)
	{
		return gameService.onTheShelfGamelistByName(name);
	}
	
	/**
     * 根据游戏名获取下架的游戏列表
     * @return
     */
	@RequestMapping(value = "pulloffshelves_gamelistbyname", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse pullOffShelvesGamelistByName(String name)
	{
		return gameService.pullOffShelvesGamelistByName(name);
	}
	
	/**
     * 获取待审核的游戏列表
     * @return
     */
	@RequestMapping(value = "unaudited_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse unauditedGamelist()
	{
		return gameService.unauditedGamelist();
	}
	
	/**
     * 获取未上架的游戏列表
     * @return
     */
	@RequestMapping(value = "notonthesshelf_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse notOnTheShelfGamelist()
	{
		return gameService.notOnTheShelfGamelist();
	}
	
	/**
     * 获取已上架的游戏列表
     * @return
     */
	@RequestMapping(value = "onthesshelf_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse onTheShelfGamelist()
	{
		return gameService.onTheShelfGamelist();
	}
	
	/**
     * 获取下架的游戏列表
     * @return
     */
	@RequestMapping(value = "pulloffshelves_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse pullOffShelvesGamelist()
	{
		return gameService.pullOffShelvesGamelist();
	}
	
	/**
     * 根据id上架游戏
     * @return
     */
	@RequestMapping(value = "onthesshelf_game", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse onThesShelfGame(Integer id)
	{
		System.out.println(id);
		return gameService.onThesShelfGame(id);
	}
	
	/**
     * 根据id下架游戏
     * @return
     */
	@RequestMapping(value = "pulloffshelves_game", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse pullOffShelvesGame(Integer id)
	{
		return gameService.pullOffShelvesGame(id);
	}
}

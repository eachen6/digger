package com.digger.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.digger.service.GameService;
import com.digger.utils.FTPSSMLoad;
import com.digger.utils.UploadUtil;
import com.digger.vo.GameVO;
import com.digger.common.Const;
import com.digger.common.ResponseCode;
import com.digger.common.ServerResponse;
import com.digger.pojo.Game;
import com.digger.pojo.User;

@Controller
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	/**
	 * 视频与图片上传
	 * @param session
	 * @param files
	 * @param name
	 * @param category
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
     * 增加游戏
     * @return
     */
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse toAddGame(HttpSession session,@RequestParam(value="files") MultipartFile[] files, 
			@RequestParam(value="name") String name,@RequestParam(value="category") String category,
			 HttpServletRequest request) throws IllegalStateException, IOException{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
		
		System.out.println(name);
		
		//增加游戏，包括上传视频、图片以及其他信息
				if (files != null && files.length > 0) {
		            //循环获取file数组中得文件
					Map returnMap = new HashMap<>();
		            for (int i = 0; i < files.length; i++) {
		                MultipartFile file = files[i];       
		                if(file.getOriginalFilename().endsWith("mp4")) {
		                	Map fileMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
		                	//return ServerResponse.createBySuccess(fileMap);
		                	returnMap.put("videourl", fileMap.get("http_url"));
		                }
		                else if(file.getOriginalFilename().endsWith("cover.jpg")) {
		                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
		                	returnMap.put("coverurl", imgMap.get("http_url"));
		                }else if(file.getOriginalFilename().endsWith("carouse.jpg")) {
		                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
		                	returnMap.put("carouse", imgMap.get("http_url"));
		                }else if(file.getOriginalFilename().endsWith("bg.jpg")) {
		                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
		                	returnMap.put("bg", imgMap.get("http_url"));
		                }
		            }
		            return ServerResponse.createBySuccess(returnMap);
				}
		
		return ServerResponse.createByErrorMessage("上传失败");
	}
	
	/**
     * 获取热销轮播图
     * @return
     */
	@RequestMapping(value = "get_hotsale_carouse", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse hotSaleCarouse()
	{
		return gameService.toGetHotSaleCarouse();
	}
	
	/**
     * 获取热销游戲集合
     * @return
     */
	@RequestMapping(value = "get_hotsale_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse hotSaleGameList()
	{
		return gameService.toGetHotSaleGameList();
	}
	
	/**
     * 获取所有游戲集合
     * @return
     */
	@RequestMapping(value = "get_total_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse totalGameList()
	{
		return gameService.toGetTotalGameList();
	}
	
	/**
     * 获取所有游戲总数
     * @return
     */
	@RequestMapping(value = "get_total_game", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse totalGame()
	{
		return gameService.toGetTotalGame();
	}
	
	/**
     * 获取特惠游戲集合
     * @return
     */
	@RequestMapping(value = "get_discount_gamelist", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse discountGameList()
	{
		return gameService.toGetDiscountGameList();
	}
	
	
	/**
	 * 获取预告游戏轮播图集合
	 * @return
	 */
	@RequestMapping(value = "get_notice_carouse", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse noticeCarouse()
	{
		return gameService.toGetNoticeCarouse();
	}
	
	/**
	 * 获取预告游戏集合
	 * @return
	 */
	@RequestMapping(value = "get_notice_gamelist", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse noticeGameList()
	{
		System.out.println("uuuuuuuuuuuuuu");
		return gameService.toGetNoticeGameList();
	}
	
	/**
	 * 获取游戏详情
	 * @return
	 */
	@RequestMapping(value = "detail/{gameid}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetGameDetail(@PathVariable(value="gameid") int gameid)
	{
		return gameService.toGetDetail(gameid);
	}
	
	/**
	 * 根据关键词提示
	 * @return
	 */
	@RequestMapping(value = "search_game_byword", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse searchGameByword(@RequestParam(value="keyword") String keyword){
		System.out.println(keyword+"ggggggggggggg");
		keyword = keyword.trim();
		if(keyword.equals("")||keyword==null){
			return ServerResponse.createBySuccess(null);
		}
		return gameService.searchGameByword(keyword);
	}
	
	/**
	 * 根据关键词搜索游戏
	 * @return
	 */
	@RequestMapping(value = "search_game_byname")
	@ResponseBody
	public ServerResponse searchGameByname(@RequestParam(value="name") String name){
		return gameService.searchGameByname(name);
	}
	
	/**
	 * 根据标签（即分类）搜索游戏
	 * @return
	 */
	@RequestMapping(value = "search_game_bycategory")
	@ResponseBody
	public ServerResponse searchGameBycategory(@RequestParam(value="name") String name){
		System.out.println(name+"kkkkkkkkkkk");
		return gameService.searchGameByname(name);
	}
}

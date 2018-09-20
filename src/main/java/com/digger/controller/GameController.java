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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.digger.service.GameService;
import com.digger.utils.FTPSSMLoad;
import com.digger.utils.UploadUtil;
import com.digger.vo.GameVO;
import com.sun.tools.classfile.Annotation.element_value;
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
     * 获取特惠游戲集合
     * @return
     */
	@RequestMapping(value = "get_discount_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse discountGameList()
	{
		return gameService.toGetDiscountGameList();
	}
}

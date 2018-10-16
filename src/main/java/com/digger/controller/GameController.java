package com.digger.controller;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.digger.service.GameService;
import com.digger.utils.FTPSSMLoad;
import com.digger.utils.UploadUtil;
import com.digger.vo.CarouseVO;
import com.digger.vo.GameVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
			HttpServletRequest request) throws IllegalStateException, IOException{
	    User user = (User) session.getAttribute(Const.CURRENT_USER);
	if(user == null){
          return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
      }
		String name = null;
		//增加游戏，包括上传视频、图片以及其他信息
				if (files.length > 0) {
					//获取视频文件名称
					for(int j = 0; j < files.length; j++) {
						String file_name=files[j].getOriginalFilename();
						System.out.println(file_name);
						String suffix = file_name.substring(file_name.lastIndexOf(".") + 1);
						System.out.println(suffix);
						if(suffix.equals("mp4")) {
						name = file_name.substring(0, file_name.lastIndexOf(".mp4"));
						}
						else if(suffix.equals("rmvb")) {
							name = file_name.substring(0, file_name.lastIndexOf(".rmvb"));
						}
						else if(suffix.equals("avi")) {
							name = file_name.substring(0, file_name.lastIndexOf(".avi"));
						}
					}
					System.out.println(name);
		            //循环获取file数组中得文件
					Map returnMap = new HashMap<>();
		            for (int i = 0; i < files.length; i++) {
		            	if(name!=null) {
		            		 MultipartFile file = files[i];       
				                if(file.getOriginalFilename().endsWith("mp4")) {
				                	Map fileMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	//return ServerResponse.createBySuccess(fileMap);
				                	returnMap.put("videourl", fileMap.get("http_url"));
				                }
				                else if(file.getOriginalFilename().endsWith("rmvb")) {
				                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	returnMap.put("videourl", imgMap.get("http_url"));
				                }
				                else if(file.getOriginalFilename().endsWith("avi")) {
				                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	returnMap.put("videourl", imgMap.get("http_url"));
				                }
				                else if(file.getOriginalFilename().endsWith("cover.jpg")) {
				                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	returnMap.put("coverurl", imgMap.get("http_url"));
				                }
				                else if(file.getOriginalFilename().endsWith("surface.jpg")) {
				                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	returnMap.put("surfaceurl", imgMap.get("http_url"));
				                }
				                else if(file.getOriginalFilename().endsWith("carouse.jpg")) {
				                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	returnMap.put("carouseurl", imgMap.get("http_url"));
				                }else if(file.getOriginalFilename().endsWith("bg.jpg")) {
				                	Map imgMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	returnMap.put("bgurl", imgMap.get("http_url"));
				                }
		            	}
		               
		            }
		            if(returnMap.size()>0)
		            return gameService.toAddGamefile(returnMap,user.getId());
		            else
		            	return ServerResponse.createByErrorMessage("上传失败");
				}
		
		return ServerResponse.createByErrorMessage("上传失败");
	}
	/**
     * 获取热销轮播图
     * @return
     */
	@RequestMapping(value = "get_hotsale_carouse", method = RequestMethod.POST)
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
     * 获取所有游戏<火爆新品>集合
     * @return
     */
	@RequestMapping(value = "get_hotnew_gamelist/{pn}", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse hotnewGameList(@PathVariable(value="pn") int pn)
	{
		//startPage是PageHelper的静态方法，参数1：默认开始页面，参数2：每页显示数据的条数
        PageHelper.startPage(pn, Const.gamecount);
        //从当前类下注入的业务层实现类userService中调用方法，该方法所在类利用注入的userDao来调用真正的查询方法查询数据库信息。
        List<CarouseVO> list = gameService.toGetHotnewGameList();
        System.out.println("1");
        //使用PageInfo包装查询页面，封装了详细的分页信息.第二个参数表示连续显示的页数
        PageInfo page = new PageInfo(list,Const.pagecount);
        return ServerResponse.createBySuccess(page);
	}
	
	/**
	 * author 高志劲
     * 获取所有游戏<本周热门>集合
     * @return
     */
	@RequestMapping(value = "get_weekhot_gamelist/{pn}", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse weekhotGameList(@PathVariable(value="pn") int pn)
	{
        PageHelper.startPage(pn, Const.gamecount);
        List<CarouseVO> list = gameService.toGetWeekhotGameList();
        System.out.println("2");
        PageInfo page = new PageInfo(list,Const.pagecount);
        return ServerResponse.createBySuccess(page);
	}
	
	/**
	 * author 高志劲
     * 获取所有游戏<最新上架>集合
     * @return
     */
	@RequestMapping(value = "get_newput_gamelist/{pn}", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse newputGameList(@PathVariable(value="pn") int pn)
	{
        PageHelper.startPage(pn, Const.gamecount);
        List<CarouseVO> list = gameService.toGetNewputGameList();
        System.out.println("3");
        PageInfo page = new PageInfo(list,Const.pagecount);
        return ServerResponse.createBySuccess(page);
	}
	
	/**
	 * author 高志劲
     * 获取所有游戏<折扣促销>集合
     * @return
     */
	@RequestMapping(value = "get_discount_gamelist/{pn}", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse mydiscountGameList(@PathVariable(value="pn") int pn)
	{
        PageHelper.startPage(pn, Const.gamecount);
        List<CarouseVO> list = gameService.toGetMydiscountGameList();
        System.out.println("4");
        PageInfo page = new PageInfo(list,Const.pagecount);
        return ServerResponse.createBySuccess(page);
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
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "detail/{gameid}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetGameDetail(HttpSession session ,@PathVariable(value="gameid") int gameid) throws UnknownHostException
	{
		String ip = (String)session.getAttribute(Const.IP);
		String myip = InetAddress.getLocalHost().getHostAddress().toString();
		System.out.println(myip);
		if(ip == null){
			session.setAttribute(Const.IP, myip);
			session.setMaxInactiveInterval(30*60);
			System.out.println("IP不同");
			gameService.addclick(gameid);            //在这里调用函数，使数据库的点击次数+1；
		}
		else if(ip.equals(ip))
		{
			System.out.println("IP相同");
                        //在这里不用调用数据库，正常返回就行
		}
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
	@RequestMapping(value = "search_game_byname/{name}/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse searchGameByname(@PathVariable(value="name") String name,@PathVariable(value="pn") int pn){
		PageHelper.startPage(pn, Const.gamecount);
        List<CarouseVO> list = gameService.searchGameByname(name);
        System.out.println("5");
        PageInfo page = new PageInfo(list,Const.pagecount);
        return ServerResponse.createBySuccess(page);
	}
	
	/**
	 * 根据标签（即分类）搜索游戏
	 * @return
	 *//*
	@RequestMapping(value = "search_game_bycategory")
	@ResponseBody
	public ServerResponse searchGameBycategory(@RequestParam(value="name") String name){
		System.out.println(name+"kkkkkkkkkkk");
		return gameService.searchGameByname(name);
	}
	
	/**
     * 富文本及游戏属性保存
     * @return
     */
	@RequestMapping(value="add1", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse gameExplain(HttpServletRequest request, Game game) throws IllegalStateException, IOException {
	
		return gameService.addGameDetails(game);
	}
	
	@RequestMapping(value="add_rich_img", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse toAddRichImg(HttpSession session,@RequestParam(value="files") MultipartFile[] files, 
			HttpServletRequest request) throws IllegalStateException, IOException{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
	if(user == null){
          return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
      }
		String name = "richtext";
				if (files != null && files.length > 0) {
				
					System.out.println(name);
		            //循环获取file数组中得文件
					Map returnMap = new HashMap<>();
		            for (int i = 0; i < files.length; i++) {
		            	if(name!=null) {
		            		 MultipartFile file = files[i];       

				                	Map fileMap = FTPSSMLoad.upload(file, request, "/"+name+"/");
				                	//return ServerResponse.createBySuccess(fileMap);
				                	returnMap.put("richimgurl", fileMap.get("http_url"));
				                
		            	}
		               
		            }
		            return ServerResponse.createBySuccess(returnMap);
				}
		
		return ServerResponse.createByErrorMessage("上传失败");
	}

	
}
   

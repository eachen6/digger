package com.digger.controller;

import java.io.File;
import java.io.IOException;

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
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse toAddGame(HttpSession session,@RequestParam(value="files") MultipartFile[] files,
			 HttpServletRequest request) throws IllegalStateException, IOException{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
		
		//增加游戏，包括上传视频、图片以及其他信息
		if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
             // 如果没有文件上传，MultipartFile也不会为null，可以通过调用getSize()方法获取文件的大小来判断是否有上传文件
                if (file.getSize() > 0) {
                    // 得到项目在服务器的真实根路径，如：/home/tomcat/webapp/项目名/images
                    //String path = session.getServletContext().getRealPath("tempfordigger");
                	String path = "F:\\tempfordigger\\";
                    // 得到文件的原始名称
                    String fileName = file.getOriginalFilename();
                    // 通过文件的原始名称，可以对上传文件类型做限制，如：只能上传jpg和png的图片文件
                    if (fileName.endsWith("mp4")) {
                        File videofile = new File(path, fileName);
                        file.transferTo(videofile);
                        System.out.println("已上传一个视频文件，路径为"+path+fileName);
                        return ServerResponse.createBySuccessMessage("上传成功");
                    }
            }
		}
		}
		return ServerResponse.createByErrorMessage("上传失败");
	}
	
	
}

package com.digger.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.mysql.fabric.xmlrpc.base.Data;

public class UploadUtil {

	public UploadUtil() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 视频上传
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String upload(MultipartFile file,File filepaths) throws IllegalStateException, IOException {
		String videoPath = "/tempfordigger/";
		
		//判断文件是否为空
		if(file.getSize() > 0) {
			//获取文件名
			String filename =  file.getOriginalFilename();
			//获取文件后缀名，判断类型
			if(filename.endsWith("mp4")) {
				//视频上传
				File videofile = new File(filepaths, filename);
                file.transferTo(videofile);
                System.out.println("已上传一个视频文件，路径为"+filepaths+filename);
                return filename+",video";
			}
			else if(filename.endsWith("jpg")) {
				//图片上传
				File imgfile = new File(filepaths, filename);
				file.transferTo(imgfile);
				System.out.println("已上传一个图片文件，路径为"+filepaths+filename);
				return filename+",img";
			}
			else {
				return "";
			}
		}
		return "";
	}
	
}

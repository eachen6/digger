package com.digger.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {

	public UploadUtil() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 视频上传
	 * @param file
	 * @return
	 */
	public String uploadVideo(MultipartFile file,String filename) {
		String videoPath = "";
		if(!file.isEmpty()) {
			try {
				String rootpath = "F:\\tempfordigger\\";
				//String filename = file.getOriginalFilename();
				videoPath = rootpath + filename;
				file.transferTo(new File(rootpath));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("上传出错");
			}
		}
		return videoPath;
	}
	
}

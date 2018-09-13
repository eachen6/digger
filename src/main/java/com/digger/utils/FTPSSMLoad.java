package com.digger.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.digger.common.Const;

public class FTPSSMLoad {
    public static Map upload(MultipartFile file,HttpServletRequest request,String remotePath) {
        String path = request.getSession().getServletContext().getRealPath("upload");

        String fileName = file.getOriginalFilename();
        Integer i = fileName.lastIndexOf(".") + 1;
        String fileExtensionName = fileName.substring(i);
        //String filePrefixName = fileName.substring(0, i-1);
        //Date d = new Date();
        //String uploadFileName = filePrefixName + DateToString.getDateString("yyyy-MM-dd", d) + UUID.randomUUID().toString() + "." + fileExtensionName;
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            FTPUtil.uploadFile(remotePath,Lists.newArrayList(targetFile));
            targetFile.delete();
            //http://localhost:8080/PartyAffairs/study/download_document.do?path=/vodeo/&filename=1.jpg
            String url = Const.DOWN_INTERFACE+"path="+remotePath+"&filename="+targetFile.getName();
            String FTPurl = Const.FTP_PREFIX+remotePath+targetFile.getName();
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFile.getName());
            fileMap.put("download_url",url);
            fileMap.put("ftp_url",url);
            fileMap.put("http_url",Const.HTTP_PREFIX+remotePath+targetFile.getName());
            return fileMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void download(HttpServletResponse response, String filePath, String fileName) {
        try {
            FTPUtil.downloadFile(filePath, fileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

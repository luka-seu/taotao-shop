package com.plasticlove.manage.service.impl;

import com.google.common.collect.Lists;
import com.plasticlove.manage.service.FileService;
import com.plasticlove.utils.FTPUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;




@Service
public class FileServiceImpl implements FileService {



    //private static Logger logger = Logger.getLogger(FileServiceImpl.class);
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功了


            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上

            targetFile.delete();
        } catch (IOException e) {
            //logger.error("上传文件异常",e);
            return null;
        }
        //A:abc.jpg
        //B:abc.jpg
        return targetFile.getName();

    }
}

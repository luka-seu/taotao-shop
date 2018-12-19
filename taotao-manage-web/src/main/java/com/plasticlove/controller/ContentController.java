package com.plasticlove.controller;

import com.google.common.collect.Lists;
import com.plasticlove.commons.EasyUIDataGridResult;


import com.plasticlove.commons.PicUploadResult;
import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.content.service.ContentService;

import com.plasticlove.pojo.TbContent;
import com.plasticlove.utils.FTPUtil;
import com.plasticlove.utils.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ContentController {
    //private static Logger logger = Logger.getLogger(ContentController.class);
    @Autowired
    private ContentService contentService;


    /*private FileService fileService;*/

    @RequestMapping(value = "/content/query/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult queryContentList(Long categoryId, int page, int rows) {
        return contentService.queryContent(categoryId, page, rows);
    }

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveContent(TbContent tbContent) {

        return contentService.saveContent(tbContent);
    }


    @RequestMapping(value = "/pic/upload", method = RequestMethod.POST)
    @ResponseBody
    public PicUploadResult uploadPic(MultipartFile uploadFile, HttpServletRequest request) {
        PicUploadResult uploadResult = new PicUploadResult();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = uploadFile.getOriginalFilename();
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
            uploadFile.transferTo(targetFile);
            //文件已经上传成功了


            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上



            String targetFileName =  targetFile.getName();




            String url = "http://121.248.55.152:8086/imgs/" + targetFileName;
            uploadResult.setUrl(url);
            targetFile.delete();
            return uploadResult;

        } catch (IOException e) {
            //logger.error("上传文件异常",e);
            return null;
        }
        //A:abc.jpg
        //B:abc.jpg

    }
}

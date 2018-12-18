package com.plasticlove.controller;

import com.plasticlove.commons.EasyUIDataGridResult;


import com.plasticlove.commons.PicUploadResult;
import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.content.service.ContentService;
import com.plasticlove.manage.service.FileService;
import com.plasticlove.pojo.TbContent;
import com.plasticlove.utils.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContentController {
    //private static Logger logger = Logger.getLogger(ContentController.class);
    @Autowired
    private ContentService contentService;

    @Autowired
    private FileService fileService;

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
        String targetFileName = fileService.upload(uploadFile, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        uploadResult.setUrl(url);
        return uploadResult;
    }
}

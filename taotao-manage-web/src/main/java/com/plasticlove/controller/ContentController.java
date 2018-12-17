package com.plasticlove.controller;

import com.plasticlove.commons.EasyUIDataGridResult;


import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.content.service.ContentService;
import com.plasticlove.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;


    @RequestMapping(value = "/content/query/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult queryContentList(Long categoryId,  int page, int rows){
        return contentService.queryContent(categoryId,page,rows);
    }

    @RequestMapping(value = "/content/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveContent(TbContent tbContent){
        return contentService.saveContent(tbContent);
    }

}

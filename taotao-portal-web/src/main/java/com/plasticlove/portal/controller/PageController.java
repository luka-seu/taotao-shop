package com.plasticlove.portal.controller;

import com.plasticlove.commons.Ad1Node;
import com.plasticlove.content.service.ContentService;
import com.plasticlove.pojo.TbContent;
import com.plasticlove.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    private ContentService contentService;


    @Value("${AD1_CATEGORY_ID}")
    private Long categoryId;

    @Value("${AD1_HEIGHT}")
    private String height;

    @Value("${AD1_HEIGHT_B}")
    private String height_B;

    @Value("${AD1_WIDTH}")
    private String width;

    @Value("${AD1_WIDTH_B}")
    private String width_B;

    @RequestMapping("/index")
    public String showIndex(Model model){

        List<TbContent> tbContentList = contentService.getContentListByCategoryID(categoryId);
        List<Ad1Node> nodes = new ArrayList<>();

        for(TbContent content:tbContentList){
            Ad1Node node = new Ad1Node();
            node.setAlt(content.getSubTitle());
            node.setHeight(height);
            node.setWidth(width);
            node.setSrc(content.getPic());
            node.setHeightB(height_B);
            node.setWidthB(width_B);
            node.setHref(content.getUrl());
            node.setSrcB(content.getPic2());
            nodes.add(node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(nodes));
        return "index";

    }
}

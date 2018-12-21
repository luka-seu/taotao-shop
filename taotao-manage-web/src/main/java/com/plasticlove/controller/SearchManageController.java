package com.plasticlove.controller;

import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.manage.service.SearchManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchManageController {
    @Autowired
    private SearchManageService searchManageService;



    @RequestMapping(value = "/index/importAll",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult importItem() throws Exception {
        return searchManageService.importItemToIndexDB();
    }
}

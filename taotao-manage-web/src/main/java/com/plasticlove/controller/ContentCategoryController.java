package com.plasticlove.controller;

import java.util.List;

import com.plasticlove.commons.EasyUITreeNode;

import com.plasticlove.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService service;
	/**
	 * url : '/content/category/list',
		animate: true,
		method : "GET",
		参数: id
	 */
	@RequestMapping(value="/content/category/list",method=RequestMethod.GET)
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		//1.引入服务
		//2.注入服务
		//3调用
		return service.getContentCategoryList(parentId);
	}
	

	 /* 添加节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	/*@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name){
		return service.createContentCategory(parentId, name);
	}*/
	
}

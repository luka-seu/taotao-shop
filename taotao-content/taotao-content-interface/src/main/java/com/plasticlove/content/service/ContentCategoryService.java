package com.plasticlove.content.service;

import java.util.List;

import com.plasticlove.commons.EasyUITreeNode;
import com.plasticlove.commons.TaotaoResult;

public interface ContentCategoryService {
	//通过节点的id查询该节点的哦子节点列表
	public List<EasyUITreeNode> getContentCategoryList(Long parentId);
	//添加内容分类
	/**
	 * @param parentId 父节点的id
	 * @param name 新增节点的名称
	 * @return
	 */
	public TaotaoResult createContentCategory(Long parentId, String name);
}

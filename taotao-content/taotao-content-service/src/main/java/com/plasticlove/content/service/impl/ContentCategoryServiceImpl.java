package com.plasticlove.content.service.impl;

import com.plasticlove.commons.EasyUITreeNode;
import com.plasticlove.content.service.ContentCategoryService;
import com.plasticlove.mapper.TbContentCategoryMapper;
import com.plasticlove.pojo.TbContentCategory;
import com.plasticlove.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper mapper;
	@Override
	public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
		//1.注入mapper
		//2.创建example
		TbContentCategoryExample example = new TbContentCategoryExample();
		//3.设置条件
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);//select * from tbcontentcategory where parent_id=1
		//4.执行查询
		List<TbContentCategory> list = mapper.selectByExample(example);
		//5.转成EasyUITreeNode 列表
		//
		List<EasyUITreeNode> nodes = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			node.setText(tbContentCategory.getName());//分类名称
			nodes.add(node);
		}
		//6.返回
		return nodes;
	}


}

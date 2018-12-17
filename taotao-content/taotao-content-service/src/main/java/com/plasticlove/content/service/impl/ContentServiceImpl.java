package com.plasticlove.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plasticlove.commons.EasyUIDataGridResult;
import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.content.service.ContentService;
import com.plasticlove.mapper.TbContentMapper;
import com.plasticlove.pojo.TbContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    public EasyUIDataGridResult queryContent(Long categoryID, Integer page, Integer rows) {
        if(page==null)page=1;
        if(rows==null)rows=30;
        PageHelper.startPage(page,rows);
        /*TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryID);*/
        List<TbContent> tbContent =contentMapper.selectByCategoryId(categoryID);

        PageInfo<TbContent> info = new PageInfo<>(tbContent);

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int)info.getTotal());
        result.setRows(info.getList());

        return result;
    }

    @Override
    public TaotaoResult saveContent(TbContent tbContent) {


        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        contentMapper.insertSelective(tbContent);

        return TaotaoResult.ok();
    }
}

package com.plasticlove.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plasticlove.commons.EasyUIDataGridResult;
import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.content.jedis.JedisClient;
import com.plasticlove.content.service.ContentService;
import com.plasticlove.mapper.TbContentMapper;
import com.plasticlove.pojo.TbContent;

import com.plasticlove.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient client;

    @Value("${CONTENT_KEY}")
    private String content_key;

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
    public List<TbContent> getContentListByCategoryID(Long categoryID) {
        try {
            String jsonContent = client.hget(content_key, categoryID + "");
            if(StringUtils.isNotBlank(jsonContent)){
                System.out.println("这里有缓存啦。。。");
                return JsonUtils.jsonToList(jsonContent,TbContent.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        List<TbContent> tbContentList =contentMapper.selectByCategoryId(categoryID);

        try {
            System.out.println("没有缓存。。。");
            client.hset(content_key,categoryID+"", JsonUtils.objectToJson(tbContentList));
        }catch (Exception e){
            e.printStackTrace();
        }

        return tbContentList;
    }


    @Override
    public TaotaoResult saveContent(TbContent tbContent) {


        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        contentMapper.insertSelective(tbContent);
        try {
            client.hdel(content_key,tbContent.getCategoryId()+"");
            System.out.println("新增时，清空缓存。。。。");
        }catch (Exception e){
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }
}

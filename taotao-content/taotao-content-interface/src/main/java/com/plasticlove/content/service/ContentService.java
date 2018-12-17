package com.plasticlove.content.service;

import com.plasticlove.commons.EasyUIDataGridResult;
import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.pojo.TbContent;

import java.util.List;

public interface ContentService {
    public EasyUIDataGridResult queryContent(Long categoryID, Integer page, Integer rows);

    public TaotaoResult saveContent(TbContent tbContent);
}

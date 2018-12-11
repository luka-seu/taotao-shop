package com.plasticlove.manage.service;



/**
 * 商品相关的处理的service
 * @title ItemService.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh 
 * @version 1.0
 */
public interface ItemService {
	
	/**
	 *  根据当前的页码和每页 的行数进行分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public com.plasticlove.commons.EasyUIDataGridResult getItemList(Integer page, Integer rows);
}

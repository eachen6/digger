package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.CategoryMapper;
import com.digger.pojo.Category;
import com.digger.pojo.Game;
import com.digger.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl  implements CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;
	
	/* 
	 * 查询所有标签（即分类）
	 * @author 高志劲
	 */
	@Override
	public ServerResponse getAllLabel() {
		List<Category> list = new ArrayList<Category>();
		list = categoryMapper.selectAllLabel();
		if(CollectionUtils.isEmpty(list)){
			return ServerResponse.createByErrorMessage("未找到相关标签！");
		}
		return  ServerResponse.createBySuccess(list);
	}

}

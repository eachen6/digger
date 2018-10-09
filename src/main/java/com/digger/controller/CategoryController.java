package com.digger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.ServerResponse;
import com.digger.service.CategoryService;
import com.digger.service.GameService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	CategoryService categoryService;
	/**
	 * 查询所有游戏标签（即分类）
	 * @return
	 */
	@RequestMapping(value = "search_alllabel", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse searchAllLabel(){
		return categoryService.getAllLabel();
	}

}

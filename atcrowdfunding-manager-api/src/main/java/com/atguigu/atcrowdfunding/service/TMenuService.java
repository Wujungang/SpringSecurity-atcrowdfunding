package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TMenu;

import java.util.List;

public interface TMenuService {

	List<TMenu> listMenuAll(); //组合父子关系

	
	List<TMenu> listMenuAllTree(); //不用组合父子关系


	void saveTMenu(TMenu menu);


	TMenu getMenuById(Integer id);


	void updateTMenu(TMenu menu);


	void deleteTMenu(Integer id);
}

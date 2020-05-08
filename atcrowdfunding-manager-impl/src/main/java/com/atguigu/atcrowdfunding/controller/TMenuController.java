package com.atguigu.atcrowdfunding.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.TMenuService;
import com.atguigu.atcrowdfunding.util.Datas;

@Controller
public class TMenuController {
	
	Logger log = LoggerFactory.getLogger(TAdminController.class);

	@Autowired
	TMenuService menuService ;
	
	
	@ResponseBody
	@RequestMapping("/menu/loadTree")
	public List<TMenu> loadTree() {
		return menuService.listMenuAllTree();
	}
	
	@RequestMapping("/menu/index")
	public String index() {
		return "menu/index";
	}
	
	@ResponseBody
	@RequestMapping("/menu/doAdd")
	public String doAdd(TMenu menu) {
		menuService.saveTMenu(menu);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/menu/getMenuById")
	public TMenu getMenuById(String id) {
		TMenu menu = menuService.selectMenuById(id);
		return menu;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/menu/doUpdate")
	public String doUpdate(TMenu menu) {
		 menuService.updateTMenu(menu);
		return "ok";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/menu/doDelete")
	public String doDelete(Integer id) {
		 menuService.deleteMenuById(id);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/menu/listPermissionIdByMenuId")
	public List<Integer> listPermissionIdByRoleId(Integer menuId) {
	
		log.debug("roleId={}",menuId);

		List<Integer> list = menuService.listPermissionIdByMenuId(menuId);
		
		return list;
	}
	
	
	@ResponseBody
	@RequestMapping("/menu/doAssignPermissionToMenu")
	public String doAssignPermissionToRole(Integer menuId,Datas ds) {
		log.debug("menuId={}",menuId);
		log.debug("menuIds={}",ds.getIds());
//		List<Integer> ids = ds.getIds();
		menuService.saveMenuAndPermissionRelationship(menuId, ds.getIds());
		return "ok";
	}
}

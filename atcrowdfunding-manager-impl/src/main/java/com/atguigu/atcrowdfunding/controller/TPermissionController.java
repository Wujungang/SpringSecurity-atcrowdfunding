package com.atguigu.atcrowdfunding.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.service.TMenuService;
import com.atguigu.atcrowdfunding.service.TPermissionService;


@Controller
public class TPermissionController {
	
	Logger log = LoggerFactory.getLogger(TPermissionController.class);
	
	@Autowired
	TPermissionService permission;
	
	@Autowired
	TMenuService menuService ;
	
	@RequestMapping("/permission/index")
	public String index() {
		return "permission/index";
	}
	
	@ResponseBody
	@RequestMapping("/permission/loadTree")
	public List<TPermission> loadTree() {
		List<TPermission> list = permission.listPermissionAllTree();
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/permission/doAdd")
	public String doAdd(TPermission per) {
		permission.savePermission(per);
		return "ok";
	}
	
	
	@ResponseBody
	@RequestMapping("/permission/getMenuById")
	public TPermission getMenuById(Integer id) {
		TPermission per = permission.selectPermissionById(id);
		return per;
	}
	
	
	@ResponseBody
	@RequestMapping("/permission/doUpdate")
	public String doUpdate(TPermission per) {
		permission.updatePermission(per);
		return "ok";
	}
	
	
	@ResponseBody
	@RequestMapping("/permission/deletePermissionById")
	public String deletePermissionById(Integer id) {
		permission.deletePermissionById(id);
		return "ok";
	}
	
}

package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.Datas;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TRoleController {
	
	Logger log = LoggerFactory.getLogger(TAdminController.class);
	
	@Autowired
	TRoleService roleService;
	
	@RequestMapping("/role/index")
	public String index(HttpSession session) {
		log.error("loginSession--{}",session);
		Object loginname =session.getAttribute(Const.LOGIN_ADMIN);
//		log.error("loginname--{}",loginname);
//		if (loginname != "zhangsan") {
//			return "index";
//		}
		return "role/index";
	}
	

	@ResponseBody
	@RequestMapping("/role/loadData")
	public PageInfo<TRole> loadData(@RequestParam(value="pageNum",required = false,defaultValue = "1") Integer pageNum,
									@RequestParam(value="pageSize",required = false,defaultValue = "2") Integer pageSize,
									@RequestParam(value="condition",required = false,defaultValue = "") String condition){
		PageHelper.startPage(pageNum,pageSize);
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("condition", condition);
		
		PageInfo<TRole> page = roleService.listRolePage(paramMap);
		return page;
	}
	
	@PreAuthorize("hasRole('程序员')")
	@ResponseBody
	@RequestMapping("/role/doAdd")
	public String doAdd(TRole role) {
		roleService.saveTRole(role);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/getRoleById")
	public TRole getRoleById(Integer id) {
		return roleService.getRoleById(id);
	}
	
	@ResponseBody
	@RequestMapping("/role/doUpdate")
	public String doUpdate(TRole role) {
		roleService.updateTRole(role);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/doDelete")
	public String doDelete(String id) {
		roleService.deleteTRole(Integer.parseInt(id));
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/doDeleteBatch")
	public String doDeleteBatch(String ids,Integer pageNum) {
		List<Integer> list = new ArrayList<Integer>();
		log.error(ids);
		String[] idsList = ids.split(",");
		for (String string : idsList) {
			int id = Integer.parseInt(string);
			list.add(id);
		}
//		roleService.deleteBatch(list);
//		HashMap<Object, Object> map = new HashMap<>();
//		map.put("status", "ok");
//		map.put("pageNum", pageNum);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/doAssignPermissionToRole")
	public String doAssignPermissionToRole(Integer roleId,Datas ds) {
		log.debug("roleId={}",roleId);
		log.debug("permissionIds={}",ds.getIds());
		roleService.saveRoleAndPermissionRelationship(roleId,ds.getIds());
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/listPermissionIdByRoleId")
	public List<Integer> listPermissionIdByRoleId(Integer roleId) {
	
		log.debug("roleId={}",roleId);
		
		List<Integer> list = roleService.listPermissionIdByRoleId(roleId);
		
		return list;
	}
	
	
	
}

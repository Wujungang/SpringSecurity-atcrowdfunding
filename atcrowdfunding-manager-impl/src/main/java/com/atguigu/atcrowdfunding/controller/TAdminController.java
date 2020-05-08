package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TAdminController {
	
	Logger log = LoggerFactory.getLogger(TAdminController.class);
	
	@Autowired
	TAdminService adminService;
	
	@Autowired
	TRoleService roleService;
	
	@RequestMapping("/admin/index")
	public String index(@RequestParam(value="pageNum",required=false,defaultValue="1") Integer pageNum,
						@RequestParam(value="pageSize",required=false,defaultValue="2") Integer pageSize,
						Model model,
						@RequestParam(value="condition",required=false,defaultValue="") String condition) {
		PageHelper.startPage(pageNum,pageSize);
		
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("condition", condition);
		
		PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);
		
		model.addAttribute("page",page);
		return "admin/index";
	}
	
	@RequestMapping("/admin/toAdd")
	public String toAdd() {
		return "admin/add";
	}
	
	@PreAuthorize("hasRole('PG - 程序员')")
	@RequestMapping("/admin/doAdd")
	public String doAdd(TAdmin admin) {
		adminService.saveTAdmin(admin);
		return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
	}
	
	@RequestMapping("/admin/toUpdate")
	public String toUpdate(Integer id,Model model,Integer pageNum) {
//		Logger log = LoggerFactory.getLogger(TAdminServiceImpl.class);
		log.debug("pageNum{},---{}",pageNum,id);
		TAdmin admin = adminService.getTAdminById(id);
		System.out.println(admin+"-----------------------------");
		model.addAttribute("admin",admin);
		return "admin/update";
	}
	
	@RequestMapping("/admin/doUpdate")
	public String doUpdate(TAdmin admin) {
		adminService.updateTAdmin(admin);
		return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
	}

	@PreAuthorize("hasRole(' PG - 程序员')")
	@RequestMapping("/admin/doDelete")
	public String deleteTAdmin(Integer id,Integer pageNum) {
		adminService.deleteTAdmin(id);
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	@RequestMapping("/admin/doDeleteBatch")
	public String delete(String ids,Integer pageNum) {
		List<Integer> idList = new ArrayList<Integer>();
		String[] split = ids.split(",");
		for (String idStr : split) {
			int id = Integer.parseInt(idStr);
			idList.add(id);
		}
		adminService.deleteBatch(idList);
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	@RequestMapping("/admin/toAssign") 
	public String toAssign(String id,Model model) {
		
		//1.查询所有角色
		List<TRole> allList = roleService.listAllRole();
		
		
		//2.根据用户id查询已经拥有的角色id
		List<Integer> roleIdList = roleService.getRoleIdByAdminId(id);
		
		
		//3.将所有角色，进行划分：
		List<TRole> assignList = new ArrayList<TRole>();
		List<TRole> unAssignList = new ArrayList<TRole>();
		
		model.addAttribute("assignList", assignList);
		model.addAttribute("unAssignList", unAssignList);
		
		for(TRole role:allList) {
			if(roleIdList.contains(role.getId())) {//4.已分配角色集合
				assignList.add(role);
			}else {//5.未分配角色集合
				unAssignList.add(role);
			}
		}
		return "admin/assignRole";
	}
	
	@ResponseBody
	@RequestMapping("/admin/doAssign")
	public String doAssign(Integer[] roleId,Integer adminId) {
//		void saveAdminAndRoleRelationship(@Param("roleId") Integer[] roleId, @Param("adminId") Integer adminId);
		log.debug("adminId={}",adminId.TYPE);
		
		for (Integer rId : roleId) {
			log.error("roleId={}",rId.TYPE);
		}
		
		roleService.saveAdminAndRoleRelationship(roleId,adminId);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/admin/doUnAssign")
	public String doUnAssign(Integer[] roleId,Integer adminId) {
		roleService.deleteAdminAndRoleRelationship(roleId,adminId);
		return "ok";
	}
	
	
}

package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	TAdminService adminService ;
	
	@Autowired
	TRoleService roleService ;
	
	Logger log = LoggerFactory.getLogger(TAdminController.class);
	
	
	@ResponseBody
	@RequestMapping("/admin/doUnAssign") 
	public String doUnAssign(Integer[] roleId,Integer adminId) {
		
		log.debug("adminId={}",adminId);
		for (Integer rId : roleId) {
			log.debug("roleId={}",rId);
		}
		
		roleService.deleteAdminAndRoleRelationship(roleId,adminId);

		return "ok";
	}
	
	//"id=5&id=7&id=8&adminId=1"
	@ResponseBody
	@RequestMapping("/admin/doAssign") 
	public String doAssign(Integer[] roleId,Integer adminId) {
		
		log.debug("adminId={}",adminId);
		for (Integer rId : roleId) {
			log.debug("roleId={}",rId);
		}
		
		roleService.saveAdminAndRoleRelationship(roleId,adminId);

		return "ok";
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
	
	@RequestMapping("/admin/doDeleteBatch") //   ids = "1,2,3,4,5";
	public String doDelete(String ids,Integer pageNum) {
		List<Integer> idList = new ArrayList<Integer>();
		
		String[] split = ids.split(",");
		
		for (String idStr : split) {
			int id = Integer.parseInt(idStr);
			idList.add(id);
		}
		
		adminService.deleteBatch(idList);
		
		
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	@RequestMapping("/admin/doDelete")
	public String doDelete(Integer id,Integer pageNum) {
		
		adminService.deleteTAdmin(id);
		
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	@RequestMapping("/admin/doUpdate")
	public String doUpdate(TAdmin admin,Integer pageNum) {
		
		adminService.updateTAdmin(admin);
		
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	
	@RequestMapping("/admin/toUpdate")
	public String toUpdate(Integer id,Model model) {
		
		TAdmin admin = adminService.getTAdminById(id);
		model.addAttribute("admin", admin);
		
		return "admin/update";
	}
	
	
	@PreAuthorize("hasRole('PM - 项目经理')")
	@RequestMapping("/admin/doAdd")
	public String doAdd(TAdmin admin) {
		
		adminService.saveTAdmin(admin);
		
		//return "redirect:/admin/index";
		return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
	}
	
	@RequestMapping("/admin/toAdd")
	public String toAdd() {
		
		return "admin/add";
	}
	
	
	
	@RequestMapping("/admin/index")
	public String index(@RequestParam(value="pageNum",required=false,defaultValue="1") Integer pageNum,
						@RequestParam(value="pageSize",required=false,defaultValue="2") Integer pageSize,
						Model model,
						@RequestParam(value="condition",required=false,defaultValue="") String condition) {
		
		log.debug("pageNum={}",pageNum);
		log.debug("pageSize={}",pageSize);
		log.debug("condition={}",condition);

		PageHelper.startPage(pageNum, pageSize);  //线程绑定
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("condition", condition);
		
		PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);
		
		model.addAttribute("page", page);
		
		return "admin/index";
	}
	
}

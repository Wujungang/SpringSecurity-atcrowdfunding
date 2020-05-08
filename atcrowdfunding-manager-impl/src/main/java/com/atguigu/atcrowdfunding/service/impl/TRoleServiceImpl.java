package com.atguigu.atcrowdfunding.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRoleExample;
import com.atguigu.atcrowdfunding.bean.TRoleExample.Criteria;
import com.atguigu.atcrowdfunding.bean.TRolePermissionExample;
import com.atguigu.atcrowdfunding.controller.TAdminController;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.mapper.TAdminRoleMapper;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.mapper.TRolePermissionMapper;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.github.pagehelper.PageInfo;

@Service
public class TRoleServiceImpl implements TRoleService {
	
	Logger log = LoggerFactory.getLogger(TRoleServiceImpl.class);
	
	@Autowired
	TRoleMapper roleMapper;
	
	@Autowired
	TAdminMapper adminMapper;
	
	@Autowired
	TAdminRoleMapper adminRoleMapper;
	
	@Autowired
	TRolePermissionMapper rolePermissionMapper;
	
//	@Override
	public PageInfo<TRole> listRolePage(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		String condition = (String)paramMap.get("condition");
		List<TRole> list = null;
		if(StringUtil.isEmpty(condition)) {
			list = roleMapper.selectByExample(null);
		}else {

			TRoleExample example = new TRoleExample();
			example.createCriteria().andNameLike("%"+condition+"%");
			list = roleMapper.selectByExample(example);
		}
		
		PageInfo<TRole> page = new PageInfo<TRole>(list,5);
		
		return page;
	}

	@Override
	public PageInfo<TRole> listRolePage(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public void saveTRole(TRole role) {
		// TODO Auto-generated method stub
		roleMapper.insertSelective(role);
	}

	@Override
	public TRole getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public void updateTRole(TRole role) {
		// TODO Auto-generated method stub
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public void deleteTRole(Integer id) {

	}

//	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		roleMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}

//	@Override
	public void deleteBatch(List<Integer> list) {
		// TODO Auto-generated method stub
		roleMapper.deleteByListBatch(list);
	}

	@Override
	public List<TRole> listAllRole() {
		// TODO Auto-generated method stub
		return roleMapper.selectByExample(null);
	}

	@Override
	public List<Integer> getRoleIdByAdminId(String id) {
		return adminRoleMapper.getRoleIdByAdminId(id);
	}

	@Override
	public void saveAdminAndRoleRelationship(Integer[] roleId, Integer adminId) {
		// TODO Auto-generated method stub
		adminMapper.saveAdminAndRoleRelationship(roleId, adminId);
	}

	@Override
	public void deleteAdminAndRoleRelationship(Integer[] roleId, Integer adminId) {
		// TODO Auto-generated method stub
		adminRoleMapper.deleteAdminAndRoleRelationship(roleId,adminId);
	}

	@Override
	public void saveRoleAndPermissionRelationship(Integer roleId, List<Integer> ids) {
		// TODO Auto-generated method stub
		TRolePermissionExample example = new TRolePermissionExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		rolePermissionMapper.deleteByExample(example);
		rolePermissionMapper.saveRoleAndPermissionRelationship(roleId,ids);
	}

	@Override
	public List<Integer> listPermissionIdByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return rolePermissionMapper.listPermissionIdByRoleId(roleId);
	}




	
	

}

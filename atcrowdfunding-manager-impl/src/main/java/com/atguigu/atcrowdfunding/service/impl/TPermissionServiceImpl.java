package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.mapper.TPermissionMapper;
import com.atguigu.atcrowdfunding.service.TPermissionService;

@Service
public class TPermissionServiceImpl implements TPermissionService{
	
	@Autowired
	TPermissionMapper permissionMapper;

	@Override
	public List<TPermission> listPermissionAllTree() {
		// TODO Auto-generated method stub
		
		return permissionMapper.selectByExample(null);
	}

	@Override
	public List<TPermission> getAllPermissions() {
		return null;
	}

	@Override
	public void savePermission(TPermission per) {
		// TODO Auto-generated method stub
		permissionMapper.insertSelective(per);
	}

	@Override
	public void deletePermission(Integer id) {

	}

	@Override
	public void editPermission(TPermission permission) {

	}

	@Override
	public TPermission getPermissionById(Integer id) {
		return null;
	}

	@Override
	public List<TPermission> getPermissionByMenuid(Integer mid) {
		return null;
	}

	@Override
	public void assignPermissionToMenu(Integer mid, List<Integer> perIdArray) {

	}

	@Override
	public TPermission selectPermissionById(Integer id) {
		// TODO Auto-generated method stub
		TPermission permission = permissionMapper.selectByPrimaryKey(id);
		return permission;
	}

	@Override
	public void updatePermission(TPermission per) {
		// TODO Auto-generated method stub
		permissionMapper.updateByPrimaryKeySelective(per);
	}

	@Override
	public void deletePermissionById(Integer id) {
		// TODO Auto-generated method stub
		permissionMapper.deleteByPrimaryKey(id);
	}

}

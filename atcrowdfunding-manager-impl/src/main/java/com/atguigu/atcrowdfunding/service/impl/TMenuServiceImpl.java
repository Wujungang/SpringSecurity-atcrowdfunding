package com.atguigu.atcrowdfunding.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.bean.TPermissionMenuExample;
import com.atguigu.atcrowdfunding.bean.TRolePermissionExample;
import com.atguigu.atcrowdfunding.mapper.TMenuMapper;
import com.atguigu.atcrowdfunding.mapper.TPermissionMenuMapper;
import com.atguigu.atcrowdfunding.service.TMenuService;

@Service
public class TMenuServiceImpl implements TMenuService {
	
	Logger log = LoggerFactory.getLogger(TMenuServiceImpl.class);
	
	@Autowired
	TMenuMapper menuMapper;
	
	@Autowired
	TPermissionMenuMapper menuPermissionMapper;
	
	

	
	public List<TMenu> listMenuAll() {
		// TODO Auto-generated method stub
		ArrayList<TMenu> menuList = new ArrayList<TMenu>();
		HashMap<Integer, TMenu> cache = new HashMap<Integer,TMenu>();
		
		List<TMenu> allList = menuMapper.selectByExample(null);
		
		for (TMenu tMenu : allList) {
			if(tMenu.getPid() == 0) {
				menuList.add(tMenu);
				cache.put(tMenu.getId(), tMenu);
			}
		}
		
		for (TMenu tMenu : allList) {
			if(tMenu.getPid() != 0) {
				Integer pid = tMenu.getPid();
				TMenu parent = cache.get(pid);
				parent.getChildren().add(tMenu);
			}
		}
		log.debug("菜单={}",menuList);
		return menuList;
	}

	@Override
	public List<TMenu> listMenuAllTree() {
		// TODO Auto-generated method stub
		return menuMapper.selectByExample(null);
	}

	@Override
	public void saveTMenu(TMenu menu) {
		// TODO Auto-generated method stub
		menuMapper.insertSelective(menu);
	}

	@Override
	public TMenu getMenuById(Integer id) {
		return null;
	}

	@Override
	public TMenu selectMenuById(String id) {
		// TODO Auto-generated method stub
		return menuMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public void updateTMenu(TMenu menu) {
		// TODO Auto-generated method stub
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void deleteTMenu(Integer id) {

	}

	@Override
	public void deleteMenuById(Integer id) {
		// TODO Auto-generated method stub
		menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Integer> listPermissionIdByMenuId(Integer menuId) {
		// TODO Auto-generated method stub
		return menuPermissionMapper.listPermissionIdByMenuId(menuId);
	}

	@Override
	public void saveMenuAndPermissionRelationship(Integer menuId, List<Integer> ids) {
		// TODO Auto-generated method stub
		TPermissionMenuExample example = new TPermissionMenuExample();
		example.createCriteria().andMenuidEqualTo(menuId);
		menuPermissionMapper.deleteByExample(example);
		menuPermissionMapper.saveMenuAndPermissionRelationship(menuId,ids);
	}


	

}
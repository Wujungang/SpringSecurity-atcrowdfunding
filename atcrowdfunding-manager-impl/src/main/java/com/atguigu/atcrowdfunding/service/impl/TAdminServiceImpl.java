package com.atguigu.atcrowdfunding.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TAdminExample;
import com.atguigu.atcrowdfunding.bean.TAdminExample.Criteria;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.util.AppDateUtils;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.github.pagehelper.PageInfo;

@Service
public class TAdminServiceImpl implements TAdminService{
	
	@Autowired
	TAdminMapper adminMapper;
	
	Logger log = LoggerFactory.getLogger(TAdminServiceImpl.class);
	@Override
	public TAdmin getTAdminByLogin(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		// 2.查询用户
		String loginacct = (String) paramMap.get("loginacct");
		String userpswd = (String) paramMap.get("userpswd");
		log.error(userpswd);

		TAdminExample example = new TAdminExample();

		example.createCriteria().andLoginacctEqualTo(loginacct);

		List<TAdmin> list = adminMapper.selectByExample(example);
		TAdmin admin = list.get(0);
		String pswd = admin.getUserpswd();
		log.error(pswd);
		if (!admin.getUserpswd().equals(MD5Util.digest(userpswd))) {
			throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
		}
		return admin;
	}

	@Override
	public PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		String condition = (String)paramMap.get("condition");
		
		TAdminExample examle = new TAdminExample();
		
		if(!"".equals(condition)) {
			examle.createCriteria().andLoginacctLike("%"+condition+"%");
			
			Criteria criteria2 = examle.createCriteria();
			criteria2.andUsernameLike("%"+condition+"%");
			
			Criteria criteria3 = examle.createCriteria();
			criteria3.andEmailLike("%"+condition+"%");
			
			examle.or(criteria2);
			examle.or(criteria3);
		}
		
		
		//examle.setOrderByClause("createtime desc");
		
		List<TAdmin> list = adminMapper.selectByExample(examle);
		
		PageInfo<TAdmin> page = new PageInfo<TAdmin>(list,5);
		
		return page;
	}
	@Override
	public void saveTAdmin(TAdmin admin) {
		// TODO Auto-generated method stub
		admin.setUserpswd(MD5Util.digest(Const.DEFAULT_USERPSWD));
		
		admin.setCreatetime(AppDateUtils.getFormatTime());
		
		//insert into t_admin(loginacct,username,email) values(?,?,?);
		//insert into t_admin(loginacct,username,email,userpswd,createtime) values(?,?,?,?,?);
		adminMapper.insertSelective(admin); //动态sql,有选择性保存。
		
	}
	@Override
	public TAdmin getTAdminById(Integer id) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPrimaryKey(id);
	}
	@Override
	public void updateTAdmin(TAdmin admin) {
		// TODO Auto-generated method stub
		adminMapper.updateByPrimaryKeySelective(admin);
		
	}
	@Override
	public void deleteTAdmin(Integer id) {
		// TODO Auto-generated method stub
		adminMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteBatch(List<Integer> idList) {
		// TODO Auto-generated method stub
		adminMapper.deleteBatch(idList);
	}
	

	
	

}

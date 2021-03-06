package com.atguigu.atcrowdfunding.mapper;

import com.atguigu.atcrowdfunding.bean.TPermissionMenu;
import com.atguigu.atcrowdfunding.bean.TPermissionMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPermissionMenuMapper {
    long countByExample(TPermissionMenuExample example);

    int deleteByExample(TPermissionMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPermissionMenu record);

    int insertSelective(TPermissionMenu record);

    List<TPermissionMenu> selectByExample(TPermissionMenuExample example);

    TPermissionMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPermissionMenu record, @Param("example") TPermissionMenuExample example);

    int updateByExample(@Param("record") TPermissionMenu record, @Param("example") TPermissionMenuExample example);

    int updateByPrimaryKeySelective(TPermissionMenu record);

    int updateByPrimaryKey(TPermissionMenu record);

	List<Integer> listPermissionIdByMenuId(Integer menuId);

	void saveMenuAndPermissionRelationship(@Param("menuId") Integer menuId, @Param("ids") List<Integer> ids);
	//void saveRoleAndPermissionRelationship(@Param("roleId") Integer roleId,@Param("ids") List<Integer> ids);

    void insertBatch(@Param("mid") Integer mid,@Param("perIdArray") List<Integer> perIdArray);

}
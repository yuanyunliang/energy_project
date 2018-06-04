package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.AdminInfo;
import com.baomidou.springwind.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * AdminInfo 表数据服务层接口
 *
 */
public interface IAdminService {

	AdminInfo login(String loginName, String password);

	void deleteUser(Long userId);

	boolean insert(AdminInfo adminInfo);

	AdminInfo selectById(@Param("id") Long id);

	boolean updateById(Long id);

	/**
	 * 根据用户名取得对应信息
	 * @param loginName
	 * @return
	 */
	AdminInfo checkName(String loginName);

	/**
	 * 查询所有用户
	 * @return	封装好信息的Page对象
	 */
	Page selectUsers();

	/**
	 * 查询所有的公司
	 * @return	封装好信息的Page对象
	 */
	Page selectCompanys();

	/**
	 * 冻结用户
	 * @param id 用户id
	 * @return 是否冻结成功
	 */
	boolean doFreeze(String id);

	/**
	 * 解冻用户
	 * @param userId 用户id
	 * @return	是否冻结成功
	 */
    boolean reFreeze(String userId);
}
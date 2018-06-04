package com.baomidou.springwind.service.impl;

import com.baomidou.framework.common.util.EncryptUtil;
import com.baomidou.framework.common.util.IDUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.AdminInfo;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.mapper.CompanyInfoMapper;
import com.baomidou.springwind.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.annotations.Log;
import com.baomidou.springwind.mapper.AdminMapper;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.IAdminService;

import java.util.List;

/**
 *
 * AdminInfo 表数据服务层接口实现类
 *
 */
@Service
public class AdminServiceImpl  implements IAdminService {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CompanyInfoMapper companyInfoMapper;

	@Log("登录")
	@Override
	public AdminInfo login(String loginName, String password) {
		password = EncryptUtil.md5(password);
		AdminInfo adminInfo1 = adminMapper.login(loginName, password);
		return adminInfo1;
	}

	@Log("删除用户")
	@Override
	public void deleteUser(Long userId) {
		// 删除用户角色，再删除用户
		roleService.deleteByUserId(userId);
		adminMapper.deleteById(userId);
	}

	@Log("添加用户")
	@Override
	public boolean insert(AdminInfo adminInfo) {
		//生成ID
		Long id = IDUtil.getID();
		adminInfo.setId(id);
		int num = adminMapper.add(adminInfo);
		if (num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public AdminInfo selectById(Long id) {
		if (id != null) {
			return adminMapper.selectById(id);
		}
		return null;
	}

	@Override
	@Log("修改管理员")
	public boolean updateById(Long id) {
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setId(id);
		Integer result = adminMapper.updateById(adminInfo);
		if (result >0 ) {
			return true;
		}
		return false;
	}

	/**
	 * 根据用户名取得对应信息
	 * @param loginName
	 * @return
	 */
	@Log("根据用户名取得用户信息")
	public AdminInfo checkName(String loginName) {
		if (loginName == null) {
			return null;
		}
		return adminMapper.checkName(loginName);
	}

	/**
	 * 查询所有用户
	 * @return	封装好信息的Page对象
	 */
	@Override
	@Log("查找所有用户")
	public Page selectUsers() {
		Page<User> page = new Page<User>();
		//取得所有用户
		List<User> userInfos = userMapper.selectUsers();
		//用户的数量
		int size = userInfos.size();
		//给Page对象设置对应的值
		page.setRecords(userInfos);
		page.setTotal(size);
		return page;
	}

	/**
	 * 查询所有的公司
	 * @return	封装好信息的Page对象
	 */
	@Override
	@Log("查找所有公司")
	public Page selectCompanys() {
		Page<User> page = new Page<User>();
		//取得所有公司
		List<User> companyInfos = companyInfoMapper.selectCompanys();
		//用户的数量
		int size = companyInfos.size();
		//给Page对象设置对应的值
		page.setRecords(companyInfos);
		page.setTotal(size);
		return page;
	}

	/**
	 * 冻结用户
	 * @param id 用户id
	 * @return 是否冻结成功
	 */
	@Override
	@Log("冻结用户")
	public boolean doFreeze(String userId) {
		//用户id的非空判断
		if (userId == null) {
			return false;
		}
		userMapper.doFreeze(userId, "1");
		return true;
	}

	/**
	 * 解冻用户
	 * @param userId 用户id
	 * @return	是否冻结成功
	 */
	@Log("解冻用户")
	@Override
	public boolean reFreeze(String userId) {
		//用户id的非空判断
		if (userId == null) {
			return false;
		}
		userMapper.doFreeze(userId, "0");
		return true;
	}
}
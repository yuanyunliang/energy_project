package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.AdminRole;
import com.baomidou.springwind.entity.Role;
import com.baomidou.springwind.mapper.RoleMapper;
import com.baomidou.springwind.mapper.AdminRoleMapper;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Role 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Override
	public void deleteByUserId(Long userId) {
		AdminRole ur = new AdminRole();
		ur.setUid(userId);
		adminRoleMapper.delete(new EntityWrapper<AdminRole>(ur));
	}

}

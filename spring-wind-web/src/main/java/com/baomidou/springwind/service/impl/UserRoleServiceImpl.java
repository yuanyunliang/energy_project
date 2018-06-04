package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.AdminRole;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.mapper.AdminRoleMapper;
import com.baomidou.springwind.service.IUserRoleService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * AdminRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<AdminRoleMapper, AdminRole> implements IUserRoleService {

	@Override
	public boolean existRoleUser( Long roleId ) {
		AdminRole ur = new AdminRole();
		ur.setRid(roleId);
		int rlt = baseMapper.selectCount(new EntityWrapper<AdminRole>(ur));
		return rlt >= 1;
	}

}
package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.AdminInfo;
import org.apache.ibatis.annotations.Param;

/**
 *
 * AdminInfo 表数据库控制层接口
 *
 */
public interface AdminMapper extends BaseMapper<AdminInfo> {


    AdminInfo login(@Param("loginName") String loginName, @Param("password") String password);

    void deleteById(@Param("id") Long id);

    int add(AdminInfo adminInfo);

    AdminInfo selectById(@Param("id") Long id);

    /**
     * 根据用户名取得信息
     * @param loginName 帐户
     * @return  用户名对应的信息
     */
    AdminInfo checkName(@Param("loginName") String loginName);
}
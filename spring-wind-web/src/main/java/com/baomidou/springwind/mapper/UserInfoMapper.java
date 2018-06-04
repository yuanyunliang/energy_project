package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.UserInfo;

import java.util.List;

/**
 * @author Blse
 * @date 2018/5/2
 * @description
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     *  添加用户
     * @param userInfo  要添加的信息
     */
    void add(UserInfo userInfo);

}

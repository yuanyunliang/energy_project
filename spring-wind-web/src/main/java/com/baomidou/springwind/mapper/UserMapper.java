package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Blse
 * @date 2018/5/2
 * @description
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 注册
     * @param user
     */
    void add(User user);

    /**
     * 校验用户名是否可用
     * @param account
     * @return
     */
    Integer checkRepeat(@Param("account") String account);

    /**
     *登录
     * @param account
     * @param password
     * @return
     */
    User login(@Param("account") String account, @Param("password") String password);

    /**
     * 根据id获取对应用户的余额
     * @param id
     * @return
     */
    float getMoney(@Param("id") String id);

    /**
     * 更新用户余额
     * @param id
     * @return
     */
    int updateMoney(@Param("id") String id, @Param("money") double money);

    /**
     * 冻结/解冻帐户
     * @param id
     * @param freeze
     * @return
     */
    int doFreeze(@Param("id") String id, @Param("freeze") String freeze);

    /**
     * 获取所有用户
     * @return  所有用户的信息
     */
    List<User> selectUsers();

    /**
     * 根据id获取对应用户的类型
     * @param userId
     * @return
     */
    String getType(String userId);
}

package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.User;

/**
 * @author Blse
 * @date 2018/5/2
 * @description
 */
public interface IUserService {

    /**
     * 用户注册
     * @param user
     */
    void add(User user, String name, String phone);

    /**
     * 校验帐户是否可用
     * @param account 待校验的帐户
     * @return
     */
    boolean checkRepeat(String account);

    /**
     * 登录
     * @param user 登陆时填写的信息
     * @return 根据信息从数据库中取得的数据
     */
    User login(User user);

    /**
     * 获取用户的余额
     * @param id    用户的id
     * @return 指定id用户的余额
     */
    double getMoney(String id);

    /**
     * 修改用户余额
     * @param id    用户id
     * @return  是否修改成功
     */
    boolean updateMoney(String id, double money);

    /**
     * 获取用户的类型
     * @param id 用户的id
     * @return 指定id用户的类型
     */
    char getType(String id);
}

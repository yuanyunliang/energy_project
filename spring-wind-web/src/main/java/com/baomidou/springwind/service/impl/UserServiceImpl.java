package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.framework.common.util.EncryptUtil;
import com.baomidou.framework.common.util.IDUtil;
import com.baomidou.springwind.entity.CompanyInfo;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.UserInfo;
import com.baomidou.springwind.mapper.CompanyInfoMapper;
import com.baomidou.springwind.mapper.UserInfoMapper;
import com.baomidou.springwind.mapper.UserMapper;
import com.baomidou.springwind.service.IUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Blse
 * @since 2018/5/2
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements IUserService {

    /**
     * 注册类型， 0表示用户
     */
    private static final char USER_TYPE = '0';

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final CompanyInfoMapper companyInfoMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserInfoMapper userInfoMapper, CompanyInfoMapper companyInfoMapper) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
        this.companyInfoMapper = companyInfoMapper;
    }

    /**
     * 用户注册
     * @param user  注册的密码，帐户等主要信息
     * @param name  注册的用户名
     * @param phone 注册的手机号
     */
    @Log("用户注册")
    @Override
    public void add(User user, String name, String phone) {
        //生成UUID
        String UUID = IDUtil.getUUID();
        //对密码进行加密
        String password = EncryptUtil.md5AndSha(user.getPassword());
        user.setPassword(password.substring(0, 32));
        //设置用户的id
        user.setId(UUID);
        // 把用户的主要信息添加到数据库
        userMapper.add(user);
        //注册的类型
        char type = user.getType();
        //判断注册类型
        if (USER_TYPE == type) {
            UserInfo userInfo = new UserInfo(UUID, phone, name);
            userInfoMapper.add(userInfo);
        } else {
            CompanyInfo companyInfo = new CompanyInfo(UUID, name, phone);
            companyInfoMapper.add(companyInfo);
        }

    }

    /**
     * 校验用户名
     * @param account 待校验的帐户
     * @return  校验结果
     */
    @Log("校验用户名")
    @Override
    public boolean checkRepeat(String account) {
        //取得与待校验的帐户相同的用户个数
        Integer count = userMapper.checkRepeat(account);
        //帐户是否已被使用过
        boolean isRepeat = count > 0;
        return isRepeat;
    }

    /**
     * 登录
     * @param user 登陆时填写的信息
     * @return  登录用户的信息
     */
    @Log("用户登录")
    @Override
    public User login(User user) {
        String password = EncryptUtil.md5AndSha(user.getPassword());
        //对密码进行加密
        user.setPassword(password.substring(0, 32));
        //根据信息从数据库中获取数据
        User logUser = userMapper.login(user.getAccount(),user.getPassword());
        return logUser;
    }

    /**
     * 获取用户余额
     * @param id    用户的id
     * @return  用户余额
     */
    @Log("获取用户的余额")
    @Override
    public double getMoney(String id) {
        //判断id是否为空，不为空则从数据库中查询余额
        if (id != null) {
            return userMapper.getMoney(id);
        }
        //若id为空则返回-1，表示错误
        return -1.0;
    }

    /**
     * 修改用户余额
     * @param id    用户id
     * @return  是否修改成功
     */
    @Log("更新余额")
    @Override
    public boolean updateMoney(String id, double money) {
        return userMapper.updateMoney(id, money) > 0;
    }

    @Override
    public char getType(String id) {
        return "1".equals(userMapper.getType(id)) ?  '1' : '0' ;
    }
}

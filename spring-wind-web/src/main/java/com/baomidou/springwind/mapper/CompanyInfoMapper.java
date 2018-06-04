package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.CompanyInfo;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Blse
 * @sin 2018/5/2
 * @description
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {

    /**
     * 注册
     * @param companyInfo
     */
    void add(CompanyInfo companyInfo);

    /**
     * 根据公司id取得其储电量
     * @param id
     * @return
     */
    Double getElectricity(@Param("id") String id);

    /**
     * 获取所有公司
     * @return
     */
    List<User> selectCompanys();

    /**
     * 根据公司id取得其余额
     * @param id
     * @return
     */
    Double getMoney(@Param("id") String id);

    /**
     * 更新公司余额
     * @param id
     * @return
     */
    int updateMoney(@Param("id") String id, @Param("money") double money);
}

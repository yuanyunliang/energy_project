package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author Blse
 * @date 2018/5/3
 * @description
 */
public interface ICompanyInfoService {

    /**
     * 根据公司id取得其储电量
     * @param id    公司id
     * @return  储电量
     */
    Double getElectricity(String id);



}

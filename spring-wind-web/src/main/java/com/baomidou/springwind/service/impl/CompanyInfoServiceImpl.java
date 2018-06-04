package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.mapper.CompanyInfoMapper;
import com.baomidou.springwind.service.ICompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Blse
 * @date 2018/5/3
 * @description
 */
@Service
public class CompanyInfoServiceImpl implements ICompanyInfoService {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    /**
     * 根据公司id取得其储电量
     * @param id    公司id
     * @return  储电量
     */
    @Override
    public Double getElectricity(String id) {
        //对公司id进行非空判断，非空则从数据库中查询余额
        if (id != null) {
            return companyInfoMapper.getElectricity(id);
        }
        return -1.0;
    }
}

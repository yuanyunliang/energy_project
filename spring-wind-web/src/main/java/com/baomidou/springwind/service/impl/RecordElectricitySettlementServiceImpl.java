package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.springwind.entity.*;
import com.baomidou.springwind.mapper.*;
import com.baomidou.springwind.service.IRecordElectricitySettlementService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import com.baomidou.springwind.utils.EncryptUtil;
import com.baomidou.springwind.utils.EnergyChainUtil;
import com.baomidou.springwind.utils.FormatNumberUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordElectricitySettlementServiceImpl extends BaseServiceImpl<RecordElectricitySettlementMapper, RecordElectricitySettlement> implements IRecordElectricitySettlementService {

    private final UserMapper userMapper;
    private final CompanyInfoMapper companyInfoMapper;
    private final MeterMapper meterMapper;
    private final RecordElectricitySettlementMapper recordElectricitySettlementMapper;

    @Autowired
    public RecordElectricitySettlementServiceImpl(UserMapper userMapper, CompanyInfoMapper companyInfoMapper, MeterMapper meterMapper, RecordElectricitySettlementMapper recordElectricitySettlementMapper) {
        this.userMapper = userMapper;
        this.companyInfoMapper = companyInfoMapper;
        this.meterMapper = meterMapper;
        this.recordElectricitySettlementMapper = recordElectricitySettlementMapper;
    }

    @Log("自动结算")
    @Override
    public Result automaticSettlement(RecordMeterConsume recordMeterConsume) {
        // 获取最新电价
        double newPrice = recordElectricitySettlementMapper.NewestPrice();
        if (newPrice <= 0) {
            System.out.println("获取最新电价异常");
            return new Result("1", "获取最新电价异常");
        }
        // 获取用户与公司ID
        String meterId = recordMeterConsume.getMeterId();
        String userId = meterMapper.getUserIdByMeterId(meterId);
        if (StringUtil.isEmpty(userId)) {
            System.out.println("获取用户ID异常");
            return new Result("1", "获取用户ID异常");
        }
        String companyId = recordMeterConsume.getCompanyId();
        if (StringUtil.isEmpty(userId)) {
            System.out.println("获取公司ID异常");
            return new Result("1", "获取公司ID异常");
        }

        // 获取用户与公司的余额
        double userMoney = FormatNumberUtil.getTwoDecimal(userMapper.getMoney(userId));
        double companytMoney = FormatNumberUtil.getTwoDecimal(companyInfoMapper.getMoney(companyId));

        // 计算交易总额
        double electricity = recordMeterConsume.getElectricity();
        if (electricity < 0) {
            System.out.println("用电量数据不正确");
            return new Result("1", "用电量数据不正确");
        }
        double account = FormatNumberUtil.getTwoDecimal(newPrice * electricity);
        // log
        System.out.println(userId + " -> " + companyId + " - " + account);

        // 结算
        if (userMoney - account < 0) {
            System.out.println("用户余额不足");
            return new Result("1", "用户余额不足");
        }
        long tradingTime = System.currentTimeMillis();
        userMapper.updateMoney(userId, userMoney - account);
        companyInfoMapper.updateMoney(companyId, companytMoney + account);

        // 上链
        String checkSum = EncryptUtil.SHA256(companyId + meterId + "!@#$" + electricity + account + tradingTime);
        AccountJson accountJson = new AccountJson(companyId, meterId, Double.toString(electricity), Double.toString(account), Long.toString(tradingTime), checkSum);
        System.out.println("结算信息上链成功");
        return new EnergyChainUtil().post("transaction.upper", accountJson);
    }

    @Log("查询用电根据用户UUID ")
    @Override
    public List<RecordElectricitySettlement> selectElectricitySettleAll() {
        return recordElectricitySettlementMapper.selectElectricitySettleAll();
    }

    @Log("查询用电根据用户UUID ")
    @Override
    public List<RecordElectricitySettlement> selectElectricitySettleByUserId(String userId) {
        return recordElectricitySettlementMapper.selectElectricitySettleByUserId(userId);
    }

    @Log("查询用电根据公司UUID ")
    @Override
    public List<RecordElectricitySettlement> selectElectricitySettleByCompanyId(String userId) {
        return recordElectricitySettlementMapper.selectElectricitySettleByCompanyId(userId);
    }

    @Override
    public PersonalReports selectPersonalReports(String userId) {
        return null;
    }

    @Override
    public CompanyReports selectCompanyReports(String userId) {
        return null;
    }
}

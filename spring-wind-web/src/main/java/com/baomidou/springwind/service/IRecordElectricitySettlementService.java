package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.*;

import java.util.List;

/**
 * <p>
 * 用电结算信息记录表 服务类
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-15
 */
public interface IRecordElectricitySettlementService  {

    /***
     * 自动结算
     * @param recordMeterConsume  获取数据
     * @return
     *
     */
    Result automaticSettlement(RecordMeterConsume recordMeterConsume);

    /**
     * 根据用户UUID查询用电情况
     * @return
     */
    List<RecordElectricitySettlement> selectElectricitySettleAll();

    /***
     * 根据用户UUID查询用电情况
     * @param userId
     * @return
     *
     */
    List<RecordElectricitySettlement> selectElectricitySettleByUserId(String userId);

    /***
     * 根据公司UUID查询用电情况
     * @param userId
     * @return
     *
     */
    List<RecordElectricitySettlement> selectElectricitySettleByCompanyId(String userId);

    /***
     * 个人报表
     * @param userId
     * @return
     */
    PersonalReports selectPersonalReports(String userId);

    /***
     * 公司报表
     * @param userId
     * @return
     */
    CompanyReports selectCompanyReports(String userId);
}

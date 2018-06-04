package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
  * 用电结算信息记录表 Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-15
 */
public interface RecordElectricitySettlementMapper extends BaseMapper<RecordElectricitySettlement> {



    /***
     *返回最新价格
     *
     */
    Double NewestPrice();

   /***
     * 自动结算
     * @param recordMeterConsume
     * @return
     *
     */
    Automaticsettlement automaticSettlement(RecordMeterConsume recordMeterConsume);


    /***
     * 根据用户UUID查询用电情况
     * @return
     */
    List<RecordElectricitySettlement> selectElectricitySettleAll();

    /***
     * 根据用户UUID查询用电情况
     * @param account
     * @return
     */
    List<RecordElectricitySettlement> selectElectricitySettleByUserId(@Param("account") String account);

    /***
     * 根据用户UUID查询用电情况
     * @param account
     * @return
     */
    List<RecordElectricitySettlement> selectElectricitySettleByCompanyId(@Param("account") String account);

    /***
     * 个人报表
     * @param account
     * @return
     */
    PersonalReports selectPersonalReports(@Param("account") String account);

    /***
     * 公司报表
     * @param account
     * @return
     */
    CompanyReports selectCompanyReports(@Param("account") String account);


}
package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.RefundData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RefundDataMapper {

    /**
     * 保存用户退款申请
     * @param refundData
     */
    void saveRefundData(RefundData refundData);

    /**
     * 用户分页获取退款信息
     * @param page
     * @param limit
     * @param userId
     * @return
     */
    List<RefundData> listRefundData(@Param("page") Integer page, @Param("limit") Integer limit, @Param("userId") String userId);

    /**
     * 计算用户的退款记录总数量
     * @param userId
     * @return
     */
    Integer countRefundData(@Param("userId") String userId);

    /**
     * 管理端查询用户退款记录，如果userId为空则是查询所有用户的退款记录
     * @param userId
     * @return
     */
    List<RefundData> queryAllRefundData(@Param("userId") String userId);
}
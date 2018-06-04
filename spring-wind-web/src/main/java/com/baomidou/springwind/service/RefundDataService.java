package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.LayuiPage;
import com.baomidou.springwind.entity.RefundData;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
public interface RefundDataService {

    /**
     * 保存用户退款申请
     * @param refundData
     */
    void saveRefundData(RefundData refundData, HttpServletRequest request);

    /**
     * 用户分页获取退款信息
     * @param page
     * @param limit
     * @param userId
     * @return
     */
    LayuiPage<RefundData> listRefundData(Integer page, Integer limit, String userId);

    /**
     * 管理端查询用户退款记录，如果userId为空则是查询所有用户的退款记录
     * @param userId
     * @return
     */
    Page<RefundData> queryAllRefundData(String userId);
}

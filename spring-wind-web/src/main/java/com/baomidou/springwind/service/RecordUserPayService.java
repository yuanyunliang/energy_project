package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.LayuiPage;
import com.baomidou.springwind.entity.RecordUserPay;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
public interface RecordUserPayService {

    /**
     * 保存用户充值信息
     * @param recordUserPay
     */
    void saveRecordUserPay(RecordUserPay recordUserPay);

    /**
     * 分页获取用户充值信息
     */
    LayuiPage<RecordUserPay> listRecordUserPay(Integer page, Integer limit, String userId);

    /**
     * 查询用户充值记录（如果userId为空则是查询所有用户）
     * @param userId
     * @return
     */
    Page<RecordUserPay> queryAllRecordUserPay(String userId);
}

package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.LayuiPage;
import com.baomidou.springwind.entity.RefundData;
import com.baomidou.springwind.mapper.RefundDataMapper;
import com.baomidou.springwind.mapper.UserMapper;
import com.baomidou.springwind.service.RefundDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
@Service
public class RefundDataServiceImpl implements RefundDataService {

    @Autowired
    private RefundDataMapper refundDataMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveRefundData(RefundData refundData, HttpServletRequest request) {
        refundData.setRefundTime(new Date());
        refundData.setRefundStatus("1");
        refundDataMapper.saveRefundData(refundData);
        // 退款成功,更新用户余额
        // 获取用户余额
        double money = userMapper.getMoney(refundData.getUserId());
        // 减少用户余额
        Double newMoney = money - refundData.getRefundAmount();
        // 更新用户的余额
        userMapper.updateMoney(refundData.getUserId(), newMoney);
    }

    @Override
    public LayuiPage<RefundData> listRefundData(Integer page, Integer limit, String userId) {
        // 新建一个分页对象
        LayuiPage<RefundData> layuiPage = new LayuiPage<RefundData>();
        // 退款记录总数
        layuiPage.setCount(refundDataMapper.countRefundData(userId));
        // 退款记录数据
        layuiPage.setData(refundDataMapper.listRefundData(page, limit, userId));
        layuiPage.setCode(0);
        return layuiPage;
    }

    @Override
    public Page<RefundData> queryAllRefundData(String userId) {
        Page<RefundData> page = new Page<RefundData>();
        List<RefundData> refundDataList = refundDataMapper.queryAllRefundData(userId);
        page.setTotal(refundDataList.size());
        page.setRecords(refundDataList);
        return page;
    }


}

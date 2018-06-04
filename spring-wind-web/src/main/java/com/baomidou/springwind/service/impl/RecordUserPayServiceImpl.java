package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.LayuiPage;
import com.baomidou.springwind.entity.RecordUserPay;
import com.baomidou.springwind.mapper.RecordUserPayMapper;
import com.baomidou.springwind.mapper.UserMapper;
import com.baomidou.springwind.service.RecordUserPayService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
@Service
public class RecordUserPayServiceImpl implements RecordUserPayService {

    @Autowired
    private RecordUserPayMapper recordUserPayMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveRecordUserPay(RecordUserPay recordUserPay) {
        // 生成订单号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String orderNumber = sdf.format(new Date()) + recordUserPay.getPayUserId().substring(0,10) + (int)(Math.random()*100);
        recordUserPay.setPayOrderNumber(orderNumber);
        // 生成支付时间
        recordUserPay.setPayTime(new Date());
        recordUserPayMapper.saveRecordUserPay(recordUserPay);
        // 充值成功,更新用户余额
        // 获取用户余额
        double money = userMapper.getMoney(recordUserPay.getPayUserId());
        // 增加用户余额
        Double newMoney = money + recordUserPay.getPayAmount();
        // 更新用户的余额
        userMapper.updateMoney(recordUserPay.getPayUserId(), newMoney);
    }

    @Override
    public LayuiPage<RecordUserPay> listRecordUserPay(Integer page, Integer limit, String userId) {
        // 新建一个分页对象
        LayuiPage<RecordUserPay> layuiPage = new LayuiPage<RecordUserPay>();
        // 查询出数据总数
        layuiPage.setCount(recordUserPayMapper.countRecordUserPay(userId));
        // 查询出相应的数据
        layuiPage.setData(recordUserPayMapper.listRecordUserPay(page, limit, userId));
        // 设置应答码
        layuiPage.setCode(0);
        return layuiPage;
    }

    @Override
    public Page<RecordUserPay> queryAllRecordUserPay(String userId) {
        List<RecordUserPay> recordUserPays = recordUserPayMapper.queryAllRecordUserPay(userId);
        Page<RecordUserPay> page = new Page<RecordUserPay>();
        page.setRecords(recordUserPays);
        page.setTotal(recordUserPays.size());
        return page;
    }

}

package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.common.JsonHelper;
import com.baomidou.springwind.common.RequestResult;
import com.baomidou.springwind.entity.LayuiPage;
import com.baomidou.springwind.entity.RecordUserPay;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.RecordUserPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
@RequestMapping(value = "pay")
@Controller
public class RecordUserPayController {

    @Autowired
    private RecordUserPayService recordUserPayService;

    @Autowired
    private HttpSession session;

    /**
     * 前往充值页面
     * @return
     */
    @RequestMapping(value = "goPayPage")
    @Permission(action = Action.Skip)
    public String goPayPage() {
        return "pay/payPage";
    }


    /**
     * 保存用户充值信息
     * @param recordUserPay
     * @return
     */
    @RequestMapping(value = "savePayData")
    @Permission(action = Action.Skip)
    @ResponseBody
    public RequestResult saveRecordUserPay(RecordUserPay recordUserPay) {
        // 从session中获取登录用户
        User user = (User)session.getAttribute("loginUser");
        // 设置用户id
        recordUserPay.setPayUserId(user.getId());
        try {
            recordUserPayService.saveRecordUserPay(recordUserPay);
            return new RequestResult(1001, "充值成功");
        } catch (Exception e) {
            return new RequestResult(1002, "未知错误，充值失败");
        }
    }

    /**
     * 用户前往查询充值信息页面
     * @return
     */
    @RequestMapping(value = "goQueryRecordUserPayPage")
    @Permission(action = Action.Skip)
    public String queryRecordUI() {
        return "pay/queryRecordUserPayPage";
    }

    /**
     * 异步获取用户充值信息
     * @param page
     * @param limit
     * @param userId
     * @return
     */
    @Permission(action = Action.Skip)
    @RequestMapping(value = "listRecordUserPay")
    @ResponseBody
    public LayuiPage<RecordUserPay> listRecordUserPay(Integer page, Integer limit, String userId) {
        // 从session中获取登录用户
        User user = (User)session.getAttribute("loginUser");
        // 设置用户id
        userId = user.getId();
        return recordUserPayService.listRecordUserPay(page, limit, userId);
    }

    /**
     * 前往管理端查询用户充值记录页面
     * @return
     */
    @RequestMapping(value = "goQueryRecordUserPayPageByManager")
    @Permission(action = Action.Skip)
    public String goQueryRecordUserPayPageByManager() {
        return "paymanager/list";
    }

    /**
     * 管理端异步获取用户充值记录
     * @param userId
     * @return
     */
    @Permission(action = Action.Skip)
    @RequestMapping(value = "listRecordUserPayByManager")
    @ResponseBody
    public String listRecordUserPayByManager(String userId) {
        String result = JsonHelper.jsonPage(recordUserPayService.queryAllRecordUserPay(userId));
        return result;
    }
}

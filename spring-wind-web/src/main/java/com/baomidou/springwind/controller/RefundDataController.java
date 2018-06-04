package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.common.JsonHelper;
import com.baomidou.springwind.common.RequestResult;
import com.baomidou.springwind.entity.LayuiPage;
import com.baomidou.springwind.entity.RefundData;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.RefundDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
@RequestMapping(value = "refund")
@Controller
public class RefundDataController{

    @Autowired
    private RefundDataService refundDataService;

    @Autowired
    private HttpSession session;

    /**
     * 前往申请退款页面
     * @return
     */
    @RequestMapping(value = "goApplyRefundPage")
    @Permission(action = Action.Skip)
    public String goApplyRefundPage() {
        return "refund/applyRefund";
    }

    /**
     * 保存退款申请信息
     * @param refundData
     * @return
     */
    @RequestMapping(value = "applyRefund")
    @Permission(action = Action.Skip)
    @ResponseBody
    public RequestResult applyRefund(RefundData refundData, HttpServletRequest request) {
        // 从session中获取登录用户
        User user = (User)session.getAttribute("loginUser");
        // 设置用户id
        refundData.setUserId(user.getId());
        try {
            refundDataService.saveRefundData(refundData, request);
            return new RequestResult(1001,"申请退款成功");
        } catch (Exception e) {
            return new RequestResult(1002, "未知错误，申请退款失败");
        }
    }

    /**
     * 用户前往查询退款信息页面
     * @return
     */
    @RequestMapping(value = "queryRefundUI")
    @Permission(action = Action.Skip)
    public String queryRefundUI() {
        return "refund/queryRefund";
    }

    /**
     * 用户异步获取退款申请信息
     * @param page
     * @param limit
     * @param userId
     * @return
     */
    @RequestMapping(value = "queryRefund")
    @ResponseBody
    @Permission(action = Action.Skip)
    public LayuiPage<RefundData> queryRefund(Integer page, Integer limit, String userId) {
        // 从session中获取登录用户
        User user = (User)session.getAttribute("loginUser");
        // 设置用户id
        userId = user.getId();
        return refundDataService.listRefundData(page, limit, userId);
    }



}

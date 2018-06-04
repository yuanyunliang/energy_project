package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.IMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 电表归属表 前端控制器
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-16
 */
@Controller
@RequestMapping("/meter")
public class MeterController extends BaseController{

    @Autowired
    private IMeterService iMeterService;

    @Permission("2000")
    @RequestMapping("/list")
    public String getMeterList() {
        return "/electric/userMeters";
    }

    @Permission("2000")
    @RequestMapping("/get")
    @ResponseBody
    public String getList(String id) {
        User user = (User)session.getAttribute("loginUser");
        return jsonPage(iMeterService.selectMeterListByUserId(user.getId()));
    }
}
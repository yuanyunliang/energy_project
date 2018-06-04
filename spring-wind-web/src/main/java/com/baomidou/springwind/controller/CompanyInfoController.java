package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.service.ICompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Blse
 * @date 2018/5/18
 * @description
 */
@Controller
@RequestMapping("/companyInfo")
public class CompanyInfoController extends BaseController{

    @Autowired
    private ICompanyInfoService companyInfoService;

    @Permission("2001")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/company/list";
    }

}

package com.baomidou.springwind.controller;

import com.baomidou.framework.common.AjaxResult;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.entity.*;
import com.baomidou.springwind.service.IRecordElectricitySettlementService;
import com.baomidou.springwind.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/dataQuery")
public class DataQueryController extends BaseController {

    private final IRecordElectricitySettlementService iRecordElectricitySettlementService;
    private final IUserService iUserService;

    @Autowired
    public DataQueryController(IRecordElectricitySettlementService iRecordElectricitySettlementService, IUserService iUserService) {
        this.iRecordElectricitySettlementService = iRecordElectricitySettlementService;
        this.iUserService = iUserService;
    }

    @Permission(action = Action.Skip)
    @RequestMapping(value = "/AutomaticClearing", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    @ResponseBody
    public Result AutomaticClearing(@RequestBody RecordMeterConsume recordMeterConsume) {
        // "{\"meterId\":\"083cadf2bac8581e9da584c52c980ea5\", \"companyId\":\"08ad206c732f5b7e859c915ffa202345\", \"electricity\":\"2.44\", \"startTime\":\"1523233333\", \"endTime\":\"1523234444\"}"
        System.out.println("================ getMeterId " + recordMeterConsume.getMeterId());
        System.out.println("================ getCompanyId " + recordMeterConsume.getCompanyId());
        System.out.println("================ getElectricity " + recordMeterConsume.getElectricity());
        System.out.println("================ getStartTime " + recordMeterConsume.getStartTime());
        System.out.println("================ getEndTime " + recordMeterConsume.getEndTime());
        System.out.println("================ getId " + recordMeterConsume.getId());
        System.out.println("自动结算");
        return iRecordElectricitySettlementService.automaticSettlement(recordMeterConsume);
    }

    @Permission(action = Action.Skip)
    @RequestMapping(value = "/list")
    public String Electricity(Model model, HttpSession session) {
        System.out.println("查询所有结算信息");

        List<RecordElectricitySettlement> recordElectricitySettlement = iRecordElectricitySettlementService.selectElectricityFindAll();
        model.addAttribute("transaction", recordElectricitySettlement);
        return "/transaction/list";
    }

    @Permission(action = Action.Skip)
    @RequestMapping("userTransaction")
    @ResponseBody
    public LayuiPage getUserTran(){
        String userId = ((User) session.getAttribute("loginUser")).getId();

        List<RecordElectricitySettlement> recordElectricitySettlement = iRecordElectricitySettlementService.selectElectricitySettleByUserId(userId);
        LayuiPage layuiPage = new LayuiPage();
        layuiPage.setCode(0);
        layuiPage.setCount(recordElectricitySettlement.size());
        return layuiPage;
    }

    @Permission(action = Action.Skip)
    @RequestMapping("companyTransaction")
    @ResponseBody
    public LayuiPage getCompanyTran(){
        String companyId = ((User) session.getAttribute("loginUser")).getId();

        List<RecordElectricitySettlement> recordElectricitySettlement = iRecordElectricitySettlementService.selectElectricitySettleByCompanyId(companyId);
        LayuiPage<RecordElectricitySettlement> layuiPage = new LayuiPage();
        layuiPage.setCode(0);
        layuiPage.setCount(recordElectricitySettlement.size());
        layuiPage.setData(recordElectricitySettlement);
        return layuiPage;
    }

    @Permission(action = Action.Skip)
    @RequestMapping("/PrintTable")
    public String PrintTable(Model model, String userId) {
        System.out.println("数据导出");

        if ('1' != iUserService.getType(userId)) { //公司为1 个人为0  默认为个人
            iRecordElectricitySettlementService.selectPersonalReports(userId);
        } else {
            iRecordElectricitySettlementService.selectCompanyReports(userId);
        }
        return "";
    }
}

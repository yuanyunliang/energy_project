package com.baomidou.springwind.controller;

import com.baomidou.framework.annotations.Log;
import com.baomidou.framework.common.AjaxResult;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.parser.FastJsonParser;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.baomidou.springwind.entity.RecordAlternatorProducer;
import com.baomidou.springwind.entity.Result;
import com.baomidou.springwind.entity.SysLog;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.IRecordAlternatorProducerService;
import com.baomidou.springwind.service.ISysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 发电机发电信息记录表 前端控制器
 * </p>
 *
 * @author
 * @since 2018-05-15
 */
@Controller
@RequestMapping("/alternatorProducer")
public class RecordAlternatorProducerController extends BaseController{

    @Autowired
    private IRecordAlternatorProducerService iRecordAlternatorProducerService;

    @Autowired
    private ISysLogService sysLogService;

    /**
     * 发电信息上链
     * @param recordAlternatorProducer 发电信息对象
     * @param request 请求对象
     * @return 返回一个状态对象
     * @throws NoSuchAlgorithmException SHA-256抛出的算法异常
     */
    @Log("发电信息上链")
    @Permission("2000")
    @RequestMapping(value = "/upper", method = RequestMethod.POST)
    public void upper(RecordAlternatorProducer recordAlternatorProducer, HttpServletRequest request) throws NoSuchAlgorithmException {
        SysLog log = new SysLog();

        if (recordAlternatorProducer.getId() != null &&
                StringUtils.isNotEmpty(recordAlternatorProducer.getCompanyId()) &&
                recordAlternatorProducer.getAlternatorId() != null &&
                recordAlternatorProducer.getStartTime() != null &&
                recordAlternatorProducer.getEndTime() != null) {

            log.setContent("发电信息不完整或者格式有误");
            log.setCrTime(new Date());
            sysLogService.insert(log);
            return;
        }

        // 上链
        Result electricUpper = iRecordAlternatorProducerService.generateElectricUpper(recordAlternatorProducer);

        // 更新到日志
        log.setContent(electricUpper.getMessage());
        log.setCrTime(new Date());
        sysLogService.insert(log);
    }


    @Permission("2000")
    @RequestMapping("/list")
    public String list() {
        return "/electric/companyAlternatorProducerInfoes";
    }

    /**
     * 查询出一个公司的所有发电上链信息
     * @return 公司所有电表上链信息集合
     */
    @Permission("2000")
    @RequestMapping("/select")
    @ResponseBody
    public String selectAllAlternators() {
        User user = (User) session.getAttribute("loginUser");
        return jsonPage(iRecordAlternatorProducerService.selectAlternatorsProducers(user.getId()));
    }
}

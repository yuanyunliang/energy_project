package com.baomidou.springwind.controller;

import java.util.Date;

import com.baomidou.springwind.common.JsonHelper;
import com.baomidou.springwind.entity.AdminInfo;
import com.baomidou.springwind.service.RefundDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.IAdminService;

/**
 * <p>
 * 用户管理相关操作
 * </p>
 *
 *
 * @Author Jack
 * @Date 2016/4/15 15:03
 */
@Controller
@RequestMapping("/perm/admin")
public class AdminController extends BaseController {

	@Autowired
	private IAdminService IAdminService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private RefundDataService refundDataService;

	@Permission("2001")
	@RequestMapping("/userList")
	public String userList(Model model) {
		return "/user/list";
	}

	@Permission("2001")
	@RequestMapping("/companyList")
	public String companyList(Model model) {
		return "/company/list";
	}




	@Permission("2001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id ) {
    	if ( id != null ) {
			model.addAttribute("user", IAdminService.selectById(id));
		}
    	model.addAttribute("roleList", roleService.selectList(null));
        return "/user/edit";
    }
    
	@ResponseBody
	@Permission("2001")
	@RequestMapping("/editUser")
	public String editUser( AdminInfo adminInfo) {
		boolean rlt = false;
		if ( adminInfo != null ) {
			adminInfo.setPassword(SaltEncoder.md5SaltEncode(adminInfo.getLoginName(), adminInfo.getPassword()));
			Long id = adminInfo.getId();
			if ( id != null ) {
				rlt = IAdminService.updateById(id);
			} else {
				adminInfo.setCrTime(new Date());
				adminInfo.setLastTime(adminInfo.getCrTime());
				rlt = IAdminService.insert(adminInfo);
			}
		}
		return callbackSuccess(rlt);
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/getUserList")
	public String getUserList() {
		String result = jsonPage(IAdminService.selectUsers());
		return result;
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/getCompanyList")
	public String getCompanyList() {
		String result = jsonPage(IAdminService.selectCompanys());
		System.out.println("result" + result);
		return result;
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/delUser/{userId}")
	public String delUser(@PathVariable Long userId) {
        IAdminService.deleteUser(userId);
		return Boolean.TRUE.toString();
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/{userId}")
	public AdminInfo getUser(@PathVariable Long userId) {
		return IAdminService.selectById(userId);
	}


	@RequestMapping(value = "queryRefundByManager")
	@ResponseBody
	@Permission("2001")
	public String queryRefundByManager(String userId) {
		System.out.println("hahaha");
		return JsonHelper.jsonPage(refundDataService.queryAllRefundData(userId));
	}

	/**
	 * 管理端前往查询用户退款信息页面
	 * @return
	 */
	@RequestMapping(value = "queryRefundUIByManager")
	@Permission("2001")
	public String queryRefundUIByManager() {
		return "refundmanager/list";
	}

	/**
	 * 设置头像
	 */
	@Permission(action = Action.Skip)
	@RequestMapping(value = "/setAvatar", method = RequestMethod.GET)
	public String setAvatar() {
		return "/user/avatar";
	}

	/**
	 * 冻结用户
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doFreeze/{userId}")
	@Permission("2001")
	public String doFreeze(@PathVariable("userId") String userId) {
		Boolean result = IAdminService.doFreeze(userId);
		return result.toString();
	}

	/**
	 * 冻结用户
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/reFreeze/{userId}")
	@Permission("2001")
	public String reFreeze(@PathVariable("userId") String userId) {
		Boolean result = IAdminService.reFreeze(userId);
		return result.toString();
	}

}

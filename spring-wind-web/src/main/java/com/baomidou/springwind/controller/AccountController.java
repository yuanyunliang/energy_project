package com.baomidou.springwind.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.baomidou.framework.common.util.EncryptUtil;
import com.baomidou.springwind.entity.AdminInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.framework.controller.SuperController;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.baomidou.springwind.common.MyCaptcha;
import com.baomidou.springwind.common.enums.UserType;
import com.baomidou.springwind.service.IAdminService;

/**
 * <p>
 * 账户相关操作
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/account")
public class AccountController extends SuperController {
	
	//锁定用户标记
	private static final String LOCKSCREEN_USER_FLAG = "LockscreenUserFlag";
	
	@Autowired
	protected IAdminService adminService;

	/**
	 * 登录
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/login")
	public String index(Model model) {
		if (isPost()) {
			String errorMsg = "用户名或密码错误";
			/**
			 * 过滤 XSS SQL 注入
			 */
			WafRequestWrapper wr = new WafRequestWrapper(request);
			String ctoken = wr.getParameter("ctoken");
			String captcha = wr.getParameter("captcha");
			boolean flag = StringUtils.isNotBlank(ctoken)
					&& StringUtils.isNotBlank(captcha)
					&& MyCaptcha.getInstance().verification(wr, ctoken, captcha);
			System.out.println("flag-->" + flag);
			if (flag) {
				String loginName = wr.getParameter("loginName"); 
				String password = wr.getParameter("password");
				/**
				 * <p>
				 * 用户存在，签名合法，登录成功
				 * <br>
				 * 进入后台
				 * </p>
				 */
				AdminInfo adminInfo = adminService.login(loginName, password);
				if (adminInfo != null) {
					SSOToken st = new SSOToken(request);
					st.setId(adminInfo.getId());
					st.setData(loginName);
					// 记住密码，设置 cookie 时长 1 周 = 604800 秒
					String rememberMe = wr.getParameter("rememberMe");
					if ("on".equals(rememberMe)) {
						request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
					}
					SSOHelper.setSSOCookie(request, response, st, true);
					return redirectTo("/index.html");
				}
			} else {
				errorMsg = "验证码错误";
			}
			model.addAttribute("errorMsg", errorMsg);
		}
		model.addAttribute(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		System.out.println("end-----");
		return "/login";
	}

	/**
	 * 注册
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/register")
	public String register(Model model, AdminInfo adminInfo) {
		if (isPost()) {
			AdminInfo existAdminInfo = adminService.checkName(adminInfo.getLoginName());
			if (existAdminInfo == null) {
				/* 演示不验证表单，用户名作为密码盐值 */
				adminInfo.setPassword(EncryptUtil.md5(adminInfo.getPassword()));
				adminInfo.setType(UserType.MEMBER.key());
				adminInfo.setCrTime(new Date());
				adminInfo.setLastTime(adminInfo.getCrTime());
				boolean rlt = adminService.insert(adminInfo);
				if (rlt) {
					/*
					 * 注册成功，自动登录进入后台
					 */
					SSOToken st = new SSOToken(request);
					st.setId(adminInfo.getId());
					st.setData(adminInfo.getLoginName());
					SSOHelper.setSSOCookie(request, response, st, true);
					return redirectTo("/index.html");
				}
			} else {
				model.addAttribute("tipMsg", "注册用户名【" + adminInfo.getLoginName() + "】已存在！");
			}
		}
		return "/register";
	}

	/**
	 * 退出
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/logout")
	public String logout(Model model) {
		SSOHelper.clearLogin(request, response);
		return redirectTo("/account/login.html");
	}

	/**
	 * 锁定
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/lockscreen")
	public String lockscreen(Model model, String password) {
		HttpSession session = request.getSession();
		String loginName = (String) session.getAttribute(LOCKSCREEN_USER_FLAG);
		if (StringUtils.isBlank(loginName)) {
			SSOToken st = SSOHelper.getToken(request);
			if (st == null) {
				/* 未登录 */
				return redirectTo("/account/login.html");				
			}
			loginName = st.getData();
			session.setAttribute(LOCKSCREEN_USER_FLAG, loginName);;
			SSOHelper.clearLogin(request, response);
		} else if (StringUtils.isNotBlank(password) && isPost()) {
			/*
			 * 锁定页面登录
			 */
			AdminInfo adminInfo = adminService.login(loginName, password);
			if (adminInfo != null) {
				/*
				 * 登录成功，进入后台
				 */
				SSOToken st = new SSOToken(request);
				st.setId(adminInfo.getId());
				st.setData(loginName);
				SSOHelper.setSSOCookie(request, response, st, true);
				return redirectTo("/index.html");
			}
		}
		
		model.addAttribute("loginName", loginName);
		return "/lockscreen";
	}

}

package com.baomidou.springwind.controller;


import com.baomidou.framework.common.AjaxResult;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Blse
 * @date 2018/4/29
 * @description     用户/公司 控制器
 */
@Controller
@RequestMapping("/user")
public class UserController{

    @Autowired
    private IUserService userService;

    @Autowired
    private HttpSession session;

    /**
     * 跳转到注册页面
     * @return
     */
    @Permission(action = Action.Skip)
    @RequestMapping("/registUI")
    public String registUI() {
        System.out.println("regist-------------------");
        return "/user/regist";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/loginUI")
    public String loginUI() {
        return "/user/login";
    }

    /**
     *  用户登录
     * @param user  登录时填写的信息
     * @param model 封装提示信息
     * @param session 用来保存登录用户的对象
     * @return  对应的视图
     */
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session) {
        //先检查用户是否已经登录

        //后台校验
        if (user.getAccount() == null || user.getPassword() == null) {
            model.addAttribute("result",new AjaxResult<User>(false,"用户名和密码不能为空"));
            return "/user/login";
        }
        //根据登录填写的信息取得对应的数据
        User loginUser = userService.login(user);
        //判断取得的数据是否为空，非空则表示登录成功，并把取得的数据保存到Session中，空则表示失败
        if (loginUser != null) {
            model.addAttribute("result", new AjaxResult(true,"登录成功"));
            session.setAttribute("loginUser", loginUser);
            session.setMaxInactiveInterval(1440 * 60);
            return "/pay/payPage";
        }else {
            model.addAttribute("result", new AjaxResult(false, "登录失败，帐户和密码不匹配！"));
            return "/user/login";
        }

    }

    /**
     * 用户注册
     * @param user 包含密码，帐户
     * @param name 用户名
     * @param phone 手机号码
     * @return  注册页面
     */
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(User user, String name, String phone, Model model) {
        boolean isRepeat = userService.checkRepeat(user.getAccount());
        if (isRepeat) {
            model.addAttribute("result", new AjaxResult(false, "该帐户已被注册，请重新填写"));
        }
        userService.add(user, name, phone);
        return "/user/regist";
    }

    /**
     * 校验用户名是否可用
     * @param account   要校验的用户名
     * @return  提示信息
     */
    @Permission(action = Action.Skip)
    @ResponseBody
    @RequestMapping(value = "/checkRepeat", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public AjaxResult checkRepeat(String account) {
        //取得校验结果，true表示不可用，false表示可用
        boolean isRepeat = userService.checkRepeat(account);
        if (isRepeat) {
            return new AjaxResult(false,"该帐户已被注册");
        }
        return new AjaxResult(true,"");
    }

    /***
     * 根据用户id获取用户余额
     * @param id    用户id
     * @return  余额
     */
    @Permission(action = Action.Skip)
    @RequestMapping("/getMoney")
    @ResponseBody
    public Double getMoney(String id) {
        // 从session中获取登录用户
        User user = (User)session.getAttribute("loginUser");
        Double money = userService.getMoney(user.getId());
        return money;
    }

    /**
     * 根据用户id修改用户余额
     * @param id    用户名
     * @param money 要修改的金额
     * @return  操作码，0表示失败，1表示成功
     */
    @Permission(action = Action.Skip)
    @RequestMapping("/updateMoney")
    @ResponseBody
    public int updateMoney( String id, double money) {
        boolean flag = userService.updateMoney(id, money);
        if (flag) {
            return 1;
        }
        return 0;
    }



    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "";
    }
}

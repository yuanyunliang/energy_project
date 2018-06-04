package com.baomidou.springwind.interceptors;

import com.baomidou.framework.common.AjaxResult;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.ICompanyInfoService;
import com.baomidou.springwind.service.IUserService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Blse
 * @date 2018/5/2
 * @description 异常警告拦截器
 */
public class AbnormalInterceptor implements HandlerInterceptor {

    private IUserService userService;

    private ICompanyInfoService companyInfoService;

    /**
     * 用户余额提示的门限值
     */
    private float minMoney;

    /**
     * 公司电能存储量提示的门限值
     */
    private float mimElectricity;


    /**
     * 不进行拦截的url的集合
     */
    private List<String> exculdeUrls;

    /**
     * 用户注册类型，0表示用户，1表示公司
     */
    private static final char USER_TYPE = '0';


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception{
        //获取当前的请求地址
        //格式是 /项目名/接口地址
        String requestUrl = request.getRequestURI();
        //格式是 /项目名
        String contextPath = request.getContextPath();
        //url就是请求的接口
        String url = requestUrl.substring(contextPath.length());
        System.out.println(url);
        //判断是否需要拦截该url
        if (exculdeUrls.contains(url)) {
            System.out.println("通过拦截器");
            return true;
        }
        //获取session对象
        HttpSession session = request.getSession();
        //获取已登录的用户对象
        User loginUser = (User) session.getAttribute("loginUser");
        //判断用户是否已经登录
        if (loginUser == null) {
            request.setAttribute("result", new AjaxResult(false, "您还没有登录或登录超时，请重新登录"));
            request.getRequestDispatcher("/user/loginUI").forward(request,response);
            return false;
        }
        //取得用户的id
        String id = loginUser.getId();
        //取得用户的类型
        char type = loginUser.getType();
        //0 表示是用电的用户
        char userType = '0';
        //根据注册类型进行不同操作
        if (USER_TYPE == type) {
            //取得用户余额
            double currentMoney = userService.getMoney(id);
            //判断是否小于给定的门限值
            if (currentMoney < minMoney) {
                request.setAttribute("result",new AjaxResult(false,"您的余额已不足，请充值！"));
            }
        } else {
            //取得公司的储电量
            double currentElectricity = companyInfoService.getElectricity(id);
            //判断是否小于给定的门限值
            if (currentElectricity < mimElectricity) {
                request.setAttribute("result",new AjaxResult(false,"您的电能存储量已不足，请注意！"));
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public IUserService getIUserService() {
        return userService;
    }

    public void setIUserService(IUserService userService) {
        this.userService = userService;
    }

    public float getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(float minMoney) {
        this.minMoney = minMoney;
    }

    public List<String> getExculdeUrls() {
        return exculdeUrls;
    }

    public void setExculdeUrls(List<String> exculdeUrls) {
        this.exculdeUrls = exculdeUrls;
    }

    public ICompanyInfoService getCompanyInfoService() {
        return companyInfoService;
    }

    public void setCompanyInfoService(ICompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    public float getMimElectricity() {
        return mimElectricity;
    }

    public void setMimElectricity(float mimElectricity) {
        this.mimElectricity = mimElectricity;
    }
}

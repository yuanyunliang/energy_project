//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baomidou.springwind.interceptors;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.web.handler.KissoDefaultHandler;
import com.baomidou.kisso.web.handler.SSOHandlerInterceptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SSOSpringInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger("SSOInterceptor");
    private SSOHandlerInterceptor handlerInterceptor;

    /**
     * 不需要拦截的模块
     */
    private List<String> exculdeModule;

    /**
     * 不需要拦截的请求
     */
    private List<String> exculdeUrls;

    public SSOSpringInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取当前的请求地址
        //格式是 /项目名/接口地址
        String requestUrl = request.getRequestURI();
        //格式是 /项目名
        String contextPath = request.getContextPath();
        //url就是请求的接口
        String url = requestUrl.substring(contextPath.length());
        System.out.println(url);
        // System.out.println("_>" + exculdeUrls.toString());
        // /admin/loginUI    取得的就是/admin，相当于模块的前缀
        int index = url.indexOf("/", 1);
        index = index > 0 ? index : url.length();
        String target = url.substring(0, index);
        //判断是否属于不需要拦截的模块
        if (exculdeModule.contains(target) || exculdeUrls.contains(url)) {
            System.out.println("success");
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            Login login = (Login)method.getAnnotation(Login.class);
            if (login != null && login.action() == Action.Skip) {
                return true;
            }

            Token token = SSOHelper.getToken(request);
            if (token == null) {
                if (HttpUtil.isAjax(request)) {
                    this.getHandlerInterceptor().preTokenIsNullAjax(request, response);
                    return false;
                }

                if (this.getHandlerInterceptor().preTokenIsNull(request, response)) {
                    logger.fine("logout. request url:" + request.getRequestURL());
                    SSOHelper.clearRedirectLogin(request, response);
                }

                return false;
            }

            request.setAttribute("SSOTokenAttr", token);
        }

        return true;
    }

    public SSOHandlerInterceptor getHandlerInterceptor() {
        return (SSOHandlerInterceptor)(this.handlerInterceptor == null ? KissoDefaultHandler.getInstance() : this.handlerInterceptor);
    }

    public void setHandlerInterceptor(SSOHandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    public List<String> getExculdeModule() {
        return exculdeModule;
    }

    public void setExculdeModule(List<String> exculdeModule) {
        this.exculdeModule = exculdeModule;
    }

    public List<String> getExculdeUrls() {
        return exculdeUrls;
    }

    public void setExculdeUrls(List<String> exculdeUrls) {
        this.exculdeUrls = exculdeUrls;
    }
}

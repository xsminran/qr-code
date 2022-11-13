package com.xs.config.filters;

import com.alibaba.fastjson2.JSONObject;
import com.xs.common.system.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(request.getMethod().equals("OPTIONS")){
                response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("token");
        String requestUrl = request.getServletPath();
        if (token!=null){
            boolean result= TokenUtil.checkToken(token); // 验证token
            if (result){
                    System.out.println("通过拦截器："+requestUrl);  // 可以输出接口是否通过拦截器
                return true;
            }
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        try {
                JSONObject json=new JSONObject();
            json.put("msg","token认证失败");
            json.put("code","500");
            response.getWriter().append(json.toString());
            System.out.println("认证失败，未通过拦截器："+requestUrl);
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}

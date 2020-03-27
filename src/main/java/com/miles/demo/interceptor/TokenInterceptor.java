package com.miles.demo.interceptor;

import com.miles.demo.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        if (request.getMethod().equals("GET")) {
            response.setStatus(HttpServletResponse.SC_OK);
            System.out.println("GET方法放行");
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("admin-token");
        if (token != null) {
            boolean result = TokenUtil.verify(token);
            if (result) {
                System.out.println("通过拦截器");
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try{
            System.out.println("认证失败，未通过拦截器");
            response.getWriter().write("error token");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}

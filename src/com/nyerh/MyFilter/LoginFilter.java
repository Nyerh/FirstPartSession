package com.nyerh.MyFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends BaseFilter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;

        String uri = request.getRequestURI();//检测uri请求
        String action = request.getParameter("action");//检测action

        Integer checkFilt = (Integer) request.getSession().getAttribute("checkFilt");//检测登录状态和免登录的变量

        System.out.println(uri);
        System.out.println(action);

        if(checkFilt!=null||uri.endsWith("login.jsp")||uri.endsWith("register.jsp")||uri.endsWith("CheckServlet")||"login".equals(action)||"register".equals(action)||"switchUser".equals(action))
        {
            chain.doFilter(req, resp);
        }
        else
        {
            request.setAttribute("msg","没登录无权限进入");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }

}

package com.nyerh.controller;

import DAO.IMPLDAO.LoginDao;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if("login".equals(action))
        {
            Mylogin(req,resp);
        }
        else if("register".equals(action))
        {
            resp.sendRedirect("register.jsp");
        }
        else if("myregister".equals(action))
        {
            Myregister(req,resp);
        }
        else if("switchUser".equals(action))
        {
            String name = (String)req.getSession().getAttribute("name");
            Cookie cookie=new Cookie("username",name);
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            req.getSession().setAttribute("checkFilt",null);//过滤器禁止通行
            resp.sendRedirect("login.jsp");
        }
    }

    private void Myregister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("passWD");
        String sex = req.getParameter("sex");
        String hobbies = req.getParameter("hobbies");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        User user =new User(username,password,sex,hobbies,phone,email,address);
        LoginDao ld=new LoginDao();
        Integer i = ld.addNewUser(user);
        if(i==1)
        {
            resp.sendRedirect("login.jsp");
        }
        else
        {
            req.setAttribute("msg1","注册失败");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
    }

    private void Mylogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("testCode");
        String codes = (String) req.getSession().getAttribute("codes");
        String setCookie = req.getParameter("setCookie");
        LoginDao ld=new LoginDao();
        Integer i = ld.FindUserByNamePass(name, password);
        if(codes.equals(code))
        {
            if (i == 1)
            {
                if (setCookie != null) {
                    Cookie cookie = new Cookie("username", name);
                    cookie.setMaxAge(60 * 60);
                    resp.addCookie(cookie);
                }
                req.getSession().setAttribute("checkFilt",1);//过滤器通行
                req.getSession().setAttribute("name", name);//保存用户名
                resp.sendRedirect("ToShowGoodsServlet?action=Show");
            } else
                {
                /*System.out.println("用户名或密码错误");*/
                req.setAttribute("msg","用户名或密码错误");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }
        else
        {
            req.setAttribute("msg","验证码输入错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        doGet(req, resp);
    }
}
package com.nyerh.controller;

import DAO.IMPLDAO.LoginDao;
import Entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ToShowGoodsServlet",urlPatterns = "/ToShowGoodsServlet")
public class ToShowGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if("Show".equals(action))
        {
            Show(req,resp);
        }
        else if("ShowAs".equals(action))
        {
            ShowAs(req,resp);
        }
        else if("delete".equals(action))
        {
            int id = Integer.parseInt(req.getParameter("id"));
            LoginDao ld=new LoginDao();
            Integer del = ld.delete(id);
            resp.sendRedirect("ToShowGoodsServlet?action=Show");
        }
        else if("modify".equals(action))
        {
            Integer id = Integer.parseInt(req.getParameter("id"));
            req.getSession().setAttribute("id",id);//根据id修改需要
            LoginDao ld=new LoginDao();
            Goods goods = ld.FindGoodsById(id);//根据id找到需要修改的商品信息在修改界面显示
            req.setAttribute("goods",goods);
            req.getSession().setAttribute("goods1",goods);
            req.getRequestDispatcher("modify.jsp").forward(req,resp);
        }
        else if("add".equals(action))
        {
            resp.sendRedirect("add.jsp");
        }
        else if("deleteAll".equals(action))
        {
            deleteAll(req,resp);
        }
    }

    private void ShowAs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choose1 = req.getParameter("choose1");
        List<Goods> goods=new ArrayList<>();
        LoginDao ld=new LoginDao();
        if("name".equals(choose1))
        {
            String findname = req.getParameter("tofind");
            findname="%"+findname+"%";
            goods = ld.ShowAsName(findname);
        }
        else if("price".equals(choose1))
        {
            String findmin = req.getParameter("tofind");
            String findmax = req.getParameter("tofind2");
            goods = ld.ShowAsPrice(findmin, findmax);

        }
        else if("stock".equals(choose1))
        {
            String findmin = req.getParameter("tofind");
            String findmax = req.getParameter("tofind2");
            goods=ld.ShowAsStock(findmin,findmax);
        }
        req.getSession().setAttribute("list",goods);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] id = req.getParameterValues("selected");
        LoginDao ld=new LoginDao();
        for (int i=0;i<id.length;i++)
        {
            ld.delete(Integer.parseInt(id[i]));
        }
        resp.sendRedirect("ToShowGoodsServlet?action=Show");
    }

    private void Show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDao ld=new LoginDao();
        List<Goods> list = ld.Show();
        for (Goods g:list) {
            System.out.println(g);
        }
        req.getSession().setAttribute("list",list);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

package com.nyerh.controller;

import DAO.IMPLDAO.LoginDao;
import Entity.Goods;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@WebServlet(name = "modifyServlet",urlPatterns = "/modifyServlet")
public class modifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            MyModify(req,resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void MyModify(HttpServletRequest req, HttpServletResponse resp) throws IOException, FileUploadException, InvocationTargetException, IllegalAccessException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(req);
        Goods goods=new Goods();
        Map<String,String> map = new HashMap();
        Integer id = (Integer)req.getSession().getAttribute("id");

        for (FileItem fileItem:list) {
            if(!fileItem.isFormField())
            {
                /*if(req.getInputStream().available()!=0) */
                    InputStream is = fileItem.getInputStream();
                    FileOutputStream fos=null;
                    ServletContext servletContext = getServletContext();//ServletContext接口，用于展示web的servlet视图
                    String path = servletContext.getRealPath("images");//获取到tomcat下的images文件夹
                    String filedName = null;
                    if ("".equals(fileItem.getName())) {
                        Goods goods1 = (Goods) req.getSession().getAttribute("goods1");
                        filedName = goods1.getPic();
                    } else {
                        UUID uuid = UUID.randomUUID();
                        filedName = uuid.toString() + "_" + fileItem.getName();
                        fos = new FileOutputStream(path + "\\" + filedName);
                        IOUtils.copy(is, fos);
                    }
                    if(fos!=null)
                    fos.close();
                    is.close();
                    //存储图片名字到goods对象中
                    goods.setPic(filedName);
/*                else
                {
                    Goods goods1 = (Goods) req.getSession().getAttribute("goods1");
                    String filedName = goods1.getPic();
                }*/
            }
            else
            {
                //getFieldName获取的是标签的 name 属性值
                String name = fileItem.getFieldName();
                //getString获取标签的 value 值
                String value = fileItem.getString("utf-8");
                map.put(name,value);
            }
        }
        BeanUtils.populate(goods,map);
        LoginDao ld=new LoginDao();
        Integer i = ld.modify(id, goods);
        if(i==1) {
            resp.sendRedirect("ToShowGoodsServlet?action=Show");
        }
        else
        {
            System.out.println("修改失败");
        }
    }
}

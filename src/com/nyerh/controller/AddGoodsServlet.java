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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@WebServlet(name = "AddGoodsServlet",urlPatterns = "/AddGoodsServlet")
public class AddGoodsServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            Myadd(req,resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void Myadd(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException, InvocationTargetException, IllegalAccessException, ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(req);//获取请求的集合
        Goods goods=new Goods();
        Map<String,String> map = new HashMap();
        for (FileItem fItem:list)
        {
            //非文本数据处理
            if(!fItem.isFormField()){
                InputStream is = fItem.getInputStream();
                ServletContext servletContext = getServletContext();//ServletContext接口，用于展示web的servlet视图
                String path = servletContext.getRealPath("images");//获取到tomcat下的images文件夹
                File file = new File(path);
                if(!file.exists()){
                    file.mkdir();
                }
                //防止重名
                UUID uuid = UUID.randomUUID();
                String filedName = uuid.toString()+"_"+fItem.getName();

                FileOutputStream fos = new FileOutputStream(path+"\\"+filedName);
                IOUtils.copy(is,fos);
                fos.close();
                is.close();
                //存储图片名字到goods对象中
                goods.setPic(filedName);
            }
            else
            {
                //getFieldName获取的是标签的 name 属性值
                String name = fItem.getFieldName();
                //getString获取标签的 value 值
                String value = fItem.getString("utf-8");
                map.put(name,value);
            }
        }
        // 把 map 集合中的 value 值对应的key根据goods对象中相等的属性名进行赋值
        BeanUtils.populate(goods,map);
        LoginDao ld=new LoginDao();
        Integer rSet = ld.addGoods(goods);
        //返回展示页面
        resp.sendRedirect("ToShowGoodsServlet?action=Show");
    }
}

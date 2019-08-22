package com.nyerh.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckServlet",urlPatterns = "/CheckServlet")
public class CheckServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int width = 100;
    int height = 50;
    // 1.图片的宽度,图片的高度,图片的颜色类型RGB
    BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // 2.通过图片对象获取画笔
    Graphics graphics = bImage.getGraphics();
    // 3.把画笔颜色设置为粉色
    graphics.setColor(Color.PINK);
    // 4.填充背景颜色
    graphics.fillRect(0,0,width,height);
    // 5.把画笔颜色设置为黑色
    graphics.setColor(Color.BLACK);
    // 6.设置字体
    graphics.setFont(new Font("黑体",Font.BOLD,20));
    Random ran = new Random();
    // 存储验证码的可变字符序列
    StringBuffer sb = new StringBuffer();
    int code = 0;
    // 7.生成随机数为验证码
    for (int i = 0;i < 4;i++){
      // 7.1 生成随机数
      code = ran.nextInt(10);
      // 7.2 把随机数添加到图片中
      graphics.drawString(code+"",20+20*i,30);
      sb.append(code);
    }
    request.getSession().setAttribute("codes",sb.toString());
    // 8.改变画笔颜色为绿色
    graphics.setColor(Color.GREEN);
    // 9.绘制干扰线
    for (int i = 0;i < 10;i++){
      // 干扰线x轴的开始,y轴的开始,x轴的结束,y轴的结束
      graphics.drawLine(ran.nextInt(width),ran.nextInt(height),ran.nextInt(width),ran.nextInt(height));
    }
    // 10.把绘制好的验证码图片通过 IO 流发送到请求方
    ImageIO.write(bImage,"jpg",response.getOutputStream());
  }
}

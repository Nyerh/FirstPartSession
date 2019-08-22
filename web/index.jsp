<%--
  Created by IntelliJ IDEA.
  User: NYERH
  Date: 2019/8/19
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript">
      function f1() {
        var selectAll = document.getElementsByName("selectAll")[0];
        var selecteds = document.getElementsByName("selected");
        for(var i=0;i<selecteds.length;i++)
        {
          if(selectAll.checked)
          {
            selecteds[i].checked=true;
          }
          else
          {
            selecteds[i].checked=false;
          }
        }
      }
      function f2() {
        var selectAll = document.getElementsByName("selectAll")[0];
        var selecteds = document.getElementsByName("selected");
        var len=selecteds.length;
        var ch=0;
        for(var i=0;i<len;i++)
        {
          if(selecteds[i].checked)
          {
            ch++;
          }
        }
        if(ch==len)
        {
          selectAll.checked=true;
        }

      }
    </script>
  </head>

  <body>
  <a href="LoginServlet?action=switchUser">注销用户</a><br>
  <form action="ToShowGoodsServlet?action=deleteAll" method="post" >
  <table border="1" cellspacing="0">
    <thead>
    <tr>
      <th>多选<input type="checkbox" name="selectAll" onclick="f1()"></th>
      <th>商品名</th>
      <th>展示图</th>
      <th>价格</th>
      <th>商品简介</th>
      <th>库存</th>
      <th>操作</th>
    </tr>
    </thead>

    <tbody>
<c:forEach items="${list}" var="goods">
    <tr>
      <td><input type="checkbox" name="selected" value="${goods.id}" onclick="f2()"></td>
      <td>${goods.name}</td>
      <td><img src="images/${goods.pic}" width="192px" height="108px"></td>
      <td>${goods.price}</td>
      <td>${goods.description}</td>
      <td>${goods.stock}</td>
      <td><a href="ToShowGoodsServlet?action=delete&id=${goods.id}">删除</a>/
        <a href="ToShowGoodsServlet?action=modify&id=${goods.id}">编辑</a></td>
    </tr>
</c:forEach>
    </tbody>

    <tfoot>
    <tr>
      <td colspan="7" style="text-align: center">
        <a href="ToShowGoodsServlet?action=add">添加商品</a>
      </td>
    </tr>
    </tfoot>
  </table><br>
    <input type="submit" value="删除所选">
  </form>
  </body>
</html>

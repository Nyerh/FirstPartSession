<%--
  Created by IntelliJ IDEA.
  User: NYERH
  Date: 2019/8/20
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
需要修改的商品:<br>
<table border="1" cellspacing="0">
    <thead>
    <tr>
        <th>商品名</th>
        <th>展示图</th>
        <th>价格</th>
        <th>商品简介</th>
        <th>库存</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${goods.name}</td>
        <td><img src="images/${goods.pic}" width="192px" height="108px"></td>
        <td>${goods.price}</td>
        <td>${goods.description}</td>
        <td>${goods.stock}</td>
    </tr>
    </tbody>
</table>
<form action="modifyServlet" method="post" enctype="multipart/form-data">
    商品名:<input type="text" name="name" value="${goods.name}"><br>
    图片:<input type="file" name="pic" ><br>
    价格：<input type="text" name="price" value="${goods.price}"><br>
    简介：<input type="text" name="description" value="${goods.description}"><br>
    库存：<input type="text" name="stock" value="${goods.stock}"><br>
    <input type="submit" value="添加">
</form>
</body>
</html>

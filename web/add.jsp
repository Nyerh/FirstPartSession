<%--
  Created by IntelliJ IDEA.
  User: NYERH
  Date: 2019/8/19
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="AddGoodsServlet" method="post" enctype="multipart/form-data">
    商品名:<input type="text" name="name"/><br>
    图片:<input type="file" name="pic"><br>
    价格：<input type="text" name="price"><br>
    简介：<input type="text" name="description"><br>
    库存：<input type="text" name="stock"><br>
    <input type="submit" value="添加">
</form>
</body>
</html>

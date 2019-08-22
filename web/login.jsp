<%--
  Created by IntelliJ IDEA.
  User: NYERH
  Date: 2019/8/19
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function NotEmp() {
            var name = document.getElementsByName("username")[0];
            var password = document.getElementsByName("password")[0];
            if(name.value==""||password.value=="")
            {
                alert("登录信息不可为空");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%
    Cookie[] cookies=request.getCookies();
    for (Cookie c:cookies) {
        if("username".equals(c.getName()))
        {
            request.getSession().setAttribute("checkFilt",1);
            response.sendRedirect("ToShowGoodsServlet?action=Show");
        }
    }
%>
${msg}
<h2 align="center"><font color=red>用户登录页面</font></h2>

<form method="post" action="LoginServlet?action=login" onsubmit="return NotEmp()">
    <table align="center" border="1">
        <tr><td>用户名</td><td><input type="text" name="username"></td></tr>
        <tr><td>密码</td><td><input type="password" name="password"></td></tr>
        <tr><td><img src="CheckServlet" onclick="this.src=this.src+'?'+Math.random()" id="img"></td>
        <td><input type="text" name="testCode"></td></tr>
        <tr><td>是否保存</td><td><input type="checkbox" name="setCookie"></td></tr>
        <tr><td colspan="2" style="text-align: center"><input type="submit" name="login" value="登录"></td></tr>
    </table>
</form>

<p align="center"><a href="LoginServlet?action=register">注册</a></p>

</body>
</html>
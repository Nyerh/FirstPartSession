<%--
  Created by IntelliJ IDEA.
  User: NYERH
  Date: 2019/8/19
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function btn()
        {
            var password = document.getElementsByName("passWD")[0];
            var repass = document.getElementsByName("rePassWD")[0];
            if(password.value!==repass.value)
            {
                alert("两次密码不同");
                return false;
            }
            return true;
        }
/**
 * @return {boolean}
 */
function NotEmp()
        {
            var name = document.getElementsByName("username")[0];
            var password = document.getElementsByName("passWD")[0];
            var hobbies = document.getElementsByName("hobbies")[0];
            var phone = document.getElementsByName("phone")[0];
            var email = document.getElementsByName("email")[0];
            if(name.value==""||password.value==""||hobbies.value==""||phone.value==""||email.value=="")
            {
                alert("注册信息不可为空");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
${msg1}
<h2 align="center"><font color=red>用户注册页面</font></h2>
<form action="LoginServlet?action=myregister" method="post" onsubmit="return (btn()&&NotEmp())">
    <table align="center" border="1">
    <tr><td>用户名</td><td colspan="2"><input type="text" name="username"></td></tr>
    <tr><td>密码</td><td colspan="2"><input type="text" name="passWD"></td></tr>
    <tr><td>确认密码</td><td colspan="2"><input type="text" name="rePassWD"></td></tr>
    <tr><td>性别</td><td><input type="radio" name="sex" value="man" checked>男</td><td><input type="radio" name="sex" value="woman">女</td></tr>
    <tr><td>兴趣爱好</td><td colspan="2"><input type="text" name="hobbies"></td></tr>
    <tr><td>电话</td><td colspan="2"><input type="text" name="phone"></td></tr>
    <tr><td>邮箱</td><td colspan="2"><input type="text" name="email"></td></tr>
    <tr><td>住址</td><td colspan="2"><select name="address">
        <option value="guangdong" selected>广东</option>
        <option value="guangxi">广西</option>
        <option value="hunan">湖南</option>
        <option value="hubei">湖北</option>
    </select></td></tr>
    <tr><td colspan="3" style="text-align: center"><input type="submit" value="注册"></td></tr>
    </table>
</form>

</body>
</html>

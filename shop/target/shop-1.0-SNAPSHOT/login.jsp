<%--
  Created by IntelliJ IDEA.
  User: 83569
  Date: 2022/11/23
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录账号</title>
</head>
<body>
<h1>登录</h1>
    <form method="post" action="login-servlet">
        用户名：<input type="text" name="userName"/><br>
        密码：<input type="password" name="password"/><br><br>
        <font color="red">
            <%
                String message = (String) request.getAttribute("message");
                if(message!=null&&!"".equals(message)){
                    out.print(message);
                }
            %>
        </font><br>
        <input type="submit" value="登录">没有账号？<a href="register.jsp">立即注册</a>
    </form>
</body>
</html>

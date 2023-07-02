<%--
  Created by IntelliJ IDEA.
  User: 83569
  Date: 2022/11/23
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册账号</title>
</head>
<body>
    <h1>注册账号</h1>
    <form  method="post" action="/userController/register-servlet">
        用户名：<input type="text" name="userName"/><br>
        密码：<input type="password" name="password"><br>
        邮箱：<input type="text" name="email"><br><br>
        <font color="red">
            <%
                String message = (String) request.getAttribute("message");
                if(message!=null&&!"".equals(message)){
                    out.print(message);
                }
            %>
        </font><br>

        <input type="submit" value="注册">
        <button type="button" onclick="cancel()">取消注册</button>
    </form>
</body>
<script type="text/javascript">
    function cancel(){
        window.location.href="/login.jsp"
    }
</script>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 83569
  Date: 2022/11/24
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
    <h1>请 ${sessionScope.user.userName} 修改密码</h1>
    <form method="post" action="/userController/update-servlet">
        旧密码:<input type="password" name="beforePwd"/><br>
        新密码:<input type="password" name="newPwd"/><br>
        <font color="red">
            <%
                String message = (String) request.getAttribute("message");
                if(message!=null&&!"".equals(message)){
                    out.print(message);
                }
            %>
        </font><br>
        <input type="submit" value="确认修改">
        <button type="button" onclick="returnBack()">取消修改</button>
    </form>
</body>
<script type="text/javascript">
    function returnBack(){
        window.location.href="/productController/home-servlet"
    }
</script>
</html>

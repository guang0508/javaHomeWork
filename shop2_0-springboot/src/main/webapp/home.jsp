<%@ page import="com.guangxi.shop.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 83569
  Date: 2022/11/23
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <h1>欢迎${sessionScope.user.userName},请随意挑选</h1>
    <div>
        <%
            if(session.getAttribute("user")==null){
                out.print("<a href=\"/login.jsp\">登录</a>");
            }else{
                out.print("<a href=\"/update.jsp\">修改密码</a>&nbsp;&nbsp;");
                out.print("<a href=\"/userController/exit-servlet\">退出登录</a>");
            }
        %>
    </div><br>
    <h3>商品分类</h3>
    <div>
        <c:forEach var="index" begin="0" end="${sessionScope.treeCategoryList.size()-1}">
                <a href="/productController/show-servlet?id=${sessionScope.treeCategoryList.get(index).id}">
                        ${sessionScope.treeCategoryList.get(index).name}
                </a>&nbsp;
        </c:forEach>
    </div>
    <div>
        <%if(session.getAttribute("secondCategoryList")!=null){%>
        <c:forEach var="index2" begin="0" end="${sessionScope.secondCategoryList.size()-1}">
            <a href="/productController/show-servlet?id=${sessionScope.secondCategoryList.get(index2).id}">
                    ${sessionScope.secondCategoryList.get(index2).name}
            </a>&nbsp;
        </c:forEach>
        <%}%>
    </div>
    <div>
        <%if(session.getAttribute("thirdCategoryList")!=null){%>
        <c:forEach var="index3" begin="0" end="${sessionScope.thirdCategoryList.size()-1}">
            <a href="/productController/show-servlet?id=${sessionScope.thirdCategoryList.get(index3).id}">
                    ${sessionScope.thirdCategoryList.get(index3).name}
            </a>&nbsp;
        </c:forEach>
        <%}%>
    </div><br><br>

    <div>
        <%if(session.getAttribute("productList")!=null){%>
        <c:forEach var="index" begin="0" end="${sessionScope.productList.size()-1}">
            <div style="float:left;width:200px" >
                <a href="">
                        ${sessionScope.productList.get(index).name}
                </a><br>
                价格：${sessionScope.productList.get(index).price}元
                销量：${sessionScope.productList.get(index).sales}件
            </div>
        </c:forEach>
        <%}%>
    </div>




</body>
</html>

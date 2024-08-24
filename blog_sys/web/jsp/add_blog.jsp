<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jinda
  Date: 2024-08-17
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>博客添加页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <style>
        input{
            width: 90%;
            height: 35px;
        }
        textarea{
            width: 90%;
        }
        select{
            width: 90%;
            height: 30px;
            text-align: center;
        }
    </style>
</head>
<body>
    <center>
        <h2 style="margin-top: 5%">添加博客</h2>
        <form action="${pageContext.request.contextPath}/AddBlogServlet" method="post">
            <table class="table table-hover" style="width: 35%;margin-top: 40px;text-align: center">
                <tr>
                    <td>标题</td>
                    <td>
                        <input type="text" name="title" placeholder="请输入博客标题">
                    </td>
                </tr>
                <tr>
                    <td>内容</td>
                    <td>
                        <textarea name="content" cols="40" rows="5" placeholder="请输入博客内容"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>博客类别</td>
                    <td>
                        <select name="tid">
                            <c:forEach var="type" items="${requestScope.types}">
                                <option value="${type.tid}">${type.tname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <input class="btn btn-success" style="width: 7%;margin-top: 20px;" type="submit" value="确认添加">
        </form>
    </center>
</body>
</html>

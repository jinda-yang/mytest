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
    <title>博客编辑页</title>
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
        <h2 style="margin-top: 5%">博客编辑</h2>
        <form action="${pageContext.request.contextPath}/UpdateBlogServlet" method="post">
            <table class="table table-hover" style="width: 35%;margin-top: 40px;text-align: center">
                <input type="hidden" name="bid" value="${requestScope.blog.bid}">
                <input type="hidden" name="pageNum" value="${requestScope.pageNum}">
                <tr>
                    <td>标题</td>
                    <td>
                        <input type="text" name="title" value="${requestScope.blog.title}" placeholder="请输入博客标题">
                    </td>
                </tr>
                <tr>
                    <td>内容</td>
                    <td>
                        <textarea name="content" cols="40" rows="5" placeholder="请输入博客内容">${requestScope.blog.content}</textarea>
                    </td>
                </tr>
                <input type="hidden" name="count" value="${requestScope.blog.count}">
                <tr>
                    <td>博客类别</td>
                    <td>
                        <select name="tid">
                            <c:forEach var="type" items="${requestScope.types}">
                                <option value="${type.tid}" ${type.tid==blog.tid ? 'selected=selected' : ''}>${type.tname}</option>
<%--                                <c:if test="${requestScope.blog.tid == type.tid}">--%>
<%--                                    <option value="${type.tid}" selected>${type.tname}</option>--%>
<%--                                </c:if>--%>
<%--                                <c:if test="${requestScope.blog.tid != type.tid}">--%>
<%--                                    <option value="${type.tid}">${type.tname}</option>--%>
<%--                                </c:if>--%>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <input class="btn btn-primary" style="width: 7%;margin-top: 20px;" type="submit" value="确认修改">
        </form>
    </center>
</body>
</html>

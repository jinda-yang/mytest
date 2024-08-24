<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jinda
  Date: 2024-08-17
  Time: 09:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>回收站页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <style>
        a{
            margin-left: 10px;
            text-decoration: none;
            color: blue;
            font-size: 15px;
        }
        a:hover{
            color: deepskyblue;
        }
        font{
            font-weight: bold;
        }
    </style>
</head>
<body style="margin-left: 10px;margin-right: 10px;">
<center>
    <h2 style="font-weight: bolder">回收站</h2>
    <a class="btn btn-info" href="${pageContext.request.contextPath}/ShowBlogServlet">返回博客列表页</a>
    <span style="margin-left: 75%">共 <font>${pageBean.total}</font> 条记录</span>
    <table class="table table-bordered" style="margin-top: 10px;">
        <tr>
            <th style="width: 22%">标题</th>
            <th style="width: 45%">内容</th>
            <th style="width: 8%">打开次数</th>
            <th style="width: 10%">博客类别</th>
            <th style="width: 15%">操作</th>
        </tr>
        <c:forEach var="blog" items="${requestScope.pageBean.beanList}">
            <tr>
                <td>${blog.title}</td>
                <td>${blog.content}</td>
                <td>${blog.count}</td>
                <td>${blog.tname}</td>
                <td>
                    <button class="btn btn-success" onclick="recovery(${blog.bid})">恢复博客</button>
<%--                    <c:if test="${blog.deleted == 1}">--%>
<%--                        <font style="color: red">已删除</font>--%>
<%--                        <a class="btn btn-success" href="${pageContext.request.contextPath}/RecoveryBlogServlet?bid=${blog.bid}&pageNum=${pageBean.pageNum}">恢复博客</a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${blog.deleted == 0}">--%>
<%--                        <font style="color: lightseagreen;">未删除</font>--%>
<%--                    </c:if>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${pageBean.total > 0}">
        <a href="${pageContext.request.contextPath}/RecycleBlogServlet?pageNum=1">首页</a>
        <c:if test="${pageBean.pageNum > 1}">
            <a href="${pageContext.request.contextPath}/RecycleBlogServlet?pageNum=${pageBean.pageNum-1}">上一页</a>
        </c:if>

        <%--页码--%>
        <c:choose>
            <c:when test="${pageBean.pageCount <= 10}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${pageBean.pageCount}"/>
            </c:when>
            <c:otherwise>
                <c:set var="begin" value="${pageBean.pageNum-5}"/>
                <c:set var="end" value="${pageBean.pageNum+4}"/>
                <c:if test="${pageBean.pageNum-5 < 1}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="10"/>
                </c:if>
                <c:if test="${pageBean.pageNum+4 > pageBean.pageCount}">
                    <c:set var="begin" value="${pageBean.pageCount-9}"/>
                    <c:set var="end" value="${pageBean.pageCount}"/>
                </c:if>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${begin}" end="${end}" step="1">
            <c:choose>
                <c:when test="${i != pageBean.pageNum}">
                    <a href="${pageContext.request.contextPath}/RecycleBlogServlet?pageNum=${i}">${i}</a>
                </c:when>
                <c:otherwise>
                    <a class="disabled text-info" style="color: black"><font>${i}</font></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${pageBean.pageNum < pageBean.pageCount}">
            <a href="${pageContext.request.contextPath}/RecycleBlogServlet?pageNum=${pageBean.pageNum+1}">下一页</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/RecycleBlogServlet?pageNum=${pageBean.pageCount}">尾页</a>
        <br/>
        当前第 <font>${pageBean.pageNum}</font> 页
        /
        共 <font>${pageBean.pageCount}</font> 页
    </c:if>
</center>
</body>
<script>
    function recovery(bid) {
        window.location.href = "${pageContext.request.contextPath}/RecoveryBlogServlet?bid="+bid;
    }
</script>
</html>

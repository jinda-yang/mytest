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
    <title>博客列表页</title>
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
    <h1>博客列表</h1>
    <a class="btn btn-info" href="${pageContext.request.contextPath}/RecycleBlogServlet">查看回收站</a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/AddBlogServlet">+ 添加博客</a>
    <a class="btn btn-danger" href="javascript:multiDel()">- 批量删除</a>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/jsp/echarts.jsp">echarts</a>
    <span style="margin-left: 90%">共 <font>${pageBean.total}</font> 条记录</span>
    <table class="table table-bordered" style="margin-top: 10px;">
        <tr>
            <th style="width: 5%">
                <input type="checkbox" id="fx" name="fx" onclick="fx()">
            </th>
            <th style="width: 22%">标题</th>
            <th style="width: 40%">内容</th>
            <th style="width: 8%">打开次数</th>
            <th style="width: 10%">博客类别</th>
            <th style="width: 15%">操作</th>
        </tr>
        <c:forEach var="blog" items="${requestScope.pageBean.beanList}">
            <tr>
                <td>
                    <input type="checkbox" name="bid" value="${blog.bid}">
                </td>
                <td>${blog.title}</td>
                <td>${blog.content}</td>
                <td>${blog.count}</td>
                <td>${blog.tname}</td>
                <td>
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/UpdateBlogServlet?bid=${blog.bid}&pageNum=${pageBean.pageNum}">再次编辑</a>
                    <a class="btn btn-danger" href="javascript:del(${blog.bid})">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    总编辑次数：<font>${requestScope.sum}</font>次
    <center>
        <c:if test="${pageBean.total > 0}">
            <a href="javascript:go(1)">首页</a>
            <c:if test="${pageBean.pageNum > 1}">
                <a href="javascript:go(${pageBean.pageNum-1})">上一页</a>
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
                <c:if test="${i == pageBean.pageNum}">
                    <a class="disabled text-info" style="color: black"><font>${i}</font></a>
                </c:if>
                <c:if test="${i != pageBean.pageNum}">
                    <a href="javascript:go(${i})">${i}</a>
                </c:if>
            </c:forEach>

            <c:if test="${pageBean.pageNum < pageBean.pageCount}">
                <a href="javascript:go(${pageBean.pageNum+1})">下一页</a>
            </c:if>
            <a href="javascript:go(${pageBean.pageCount})">尾页</a>
            <br/>
            当前第 <font>${pageBean.pageNum}</font> 页
            /
            共 <font>${pageBean.pageCount}</font> 页
        </c:if>
    </center>
</body>
<script>
    function go(pageNum) {
        // alert(pageNum)
        window.location.href = "${pageContext.request.contextPath}/ShowBlogServlet?pageNum="+pageNum;
    }
    function del(bid) {
        if (confirm("确定要删除吗？")) {
            window.location.href = "${pageContext.request.contextPath}/DeleteBlogServlet?bid="+bid;
        } else {
            alert("已取消");
        }
    }
    function multiDel() {
        var bids = document.getElementsByName("bid");
        var count = 0;
        for (var i = 0; i < bids.length; i++) {
            if (bids[i].checked) {
                count++;
            }
        }
        if (count > 0) {
            if (confirm("确定要删除吗？")) {
                var ids = "";
                for (var i = 0; i < bids.length; i++) {
                    if (bids[i].checked) {
                        ids += bids[i].value+"-";
                    }
                }
                // console.log(ids);
                window.location.href = "${pageContext.request.contextPath}/MultiDelBlogServlet?bids="+ids;
            } else {
                alert("已取消")
            }
        } else {
            alert("请至少选择一项进行删除！");
        }
    }
    function fx() {
        var fx = document.getElementById("fx");
        console.log(fx.checked)
        var bids = document.getElementsByName("bid");
        if (fx.checked) {
            for (var i = 0; i < bids.length; i++) {
                bids[i].checked = true;
            }
        } else {
            for (var i = 0; i < bids.length; i++) {
                bids[i].checked = false;
            }
        }
    }
</script>
</html>

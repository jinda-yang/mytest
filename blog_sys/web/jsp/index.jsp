<%--
  Created by IntelliJ IDEA.
  User: Jinda
  Date: 2024-08-17
  Time: 09:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>
</body>
<script>
    window.onload = function () {
        window.location.href = "${pageContext.request.contextPath}/ShowBlogServlet";
    }
</script>
</html>

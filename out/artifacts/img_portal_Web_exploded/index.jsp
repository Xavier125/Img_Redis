<%--
  Created by IntelliJ IDEA.
  User: 38767
  Date: 2019/3/9
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图片管理</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>图片</th>
    </tr>
    <c:forEach items="${imageList}" var="image">
        <tr>
            <td>${image.id}</td>
            <td>
                <img src="${image.url}" alt="图片">
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

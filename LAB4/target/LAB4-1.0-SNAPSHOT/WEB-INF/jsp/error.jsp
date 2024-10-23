<%-- 
    Document   : error
    Created on : 29 paź 2023, 14:22:11
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <title>JSP Page</title>
</head>
<body>
<h1>Error Page</h1>
<p>Failed URL: ${url}
    Exception: ${exception.message}
    <c:forEach items="${exception.stackTrace}" var="ste">
        ${ste}
    </c:forEach>
</p>
<a href="/LAB4/">Powrot</a><br />
</body>
</html>
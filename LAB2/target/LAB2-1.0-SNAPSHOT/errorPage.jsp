<%-- 
    Document   : errorPage
    Created on : 17 paź 2023, 08:18:36
    Author     : mrkaszka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR Page</title>
    </head>
    <body>
        <h2>Wprowadzono błędne dane!</h2>
        <p>Pojawił się następujący błąd:<br>
        <%= exception.getMessage() %>. <br />
        </p>
        <a href="/LAB2/calc.jsp">Powrót do kalkulatora</a>
    </body>
</html>

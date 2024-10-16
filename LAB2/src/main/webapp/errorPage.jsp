<%-- 
    Document   : errorPage
    Created on : 17 paź 2023, 08:18:36
    Author     : student
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
        <a href="http://localhost:8080/LAB2_war/calc.jsp?kwota=1000.0&procent=10.0&lrat=10&wyslij=wyslij">Powrót do kalkulatora</a>
    </body>
</html>

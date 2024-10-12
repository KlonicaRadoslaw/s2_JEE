<%-- 
    Document   : calc
    Created on : 14 paź 2023, 16:58:59
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String date = dateFormat.format(now);
        out.println(date);
    %>
        <jsp:useBean id="loan" class="com.mycompany.lab2.LoanBean" scope="session" />
        <jsp:setProperty name="loan" property="*" />
        <h1>Kalkulator rat</h1>
        <div>
            <form>
                <label for="kwota">Kwota pożyczki:</label>
                <input type="text" name="kwota" value="<%= loan.getKwota() %>"/><br>
                <label for="procent">Procent roczny:</label>
                <input type="text" name="procent" value="<%= loan.getProcent() %>"/><br>
                <label for="lrat">Liczba rat</label>
                <input type="text" name="lrat" value="<%= loan.getLrat() %>"/><br>
                <input type="submit" name="wyslij" value="wyslij"/>
            </form>
        </div>
        <div>Rata miesięczna: <%= loan.getRata() %></div>
    </body>
</html>

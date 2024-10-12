<%-- 
    Document   : countryList
    Created on : 21 paź 2023, 19:08:43
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.lab3.CountryBean" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Country List</h1>
        <table>
        <tr>
            <th>Name</th>
            <th>Code</th>
            <th>Population</th>
            <th>Details</th>
        </tr>
        <% ArrayList<CountryBean> list = (ArrayList<CountryBean>)session.getAttribute("list");
           if(list != null) {
           for( CountryBean country: list){
        %>
            <tr>
                <td><%= country.getName() %></td>
                <td><%= country.getCode() %></td>
                <td><%= country.getPopulation() %></td>
                <td><a href="details.jsp?index=<%= list.indexOf(country) %>">Details</a></td>
            </tr>
        <%
               }
           }
        %>
    </table>
    </body>
</html>

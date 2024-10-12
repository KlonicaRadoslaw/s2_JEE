<%-- 
    Document   : details
    Created on : 21 paź 2023, 19:08:49
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
         <%
            ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("list");
            if(request.getParameter("index")==null || request.getParameter("index")==""){
            %>
        <p>Błąd parametru</p>
        <%
             }
             else{
            int index = Integer.parseInt(request.getParameter("index"));
            
            if (list != null && index >= 0 && index < list.size()) {
                CountryBean country = list.get(index);
        %>
        <p>Details of <%= country.getName() %></p>
        <p>Country code: <%= country.getCode() %></p>
        <p>Population: <%= country.getPopulation() %></p>
        <p>Surface area: <%= country.getSurfaceArea() %></p>
        <a href="ListServlet">Country List</a>
        <%
            }
        else{
        %>
        <p>Brak listy lub niepoprawny index</p>
        <%
        }
        }
        
        %>
    </body>
</html>

<%-- 
    Document   : home
    Created on : Nov 2, 2018, 9:44:53 AM
    Author     : buith
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center><h2>Home Page</h2></center>
        <% 
            User user = (User) request.getSession().getAttribute("user");
        
            User demo = (User) request.getAttribute("demo");
        
        %>
        Welcome <%= user.getUserName() %> 
        <p>Demo <%= demo.getUserName() %> </p>
        <div style="text-align: right"><a href="logout">Logout</a></div>
    </body>
</html>

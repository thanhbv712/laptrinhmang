<%-- 
    Document   : ranking.jsp
    Created on : Nov 2, 2018, 9:40:50 PM
    Author     : buith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ranking</title>
    </head>
    <body>
        <jsp:useBean id = "user" class = "model.User" scope="session"/>
        <div>Name is</div>
        <jsp:getProperty name="user" property="userName"/>
    </body>
</html>

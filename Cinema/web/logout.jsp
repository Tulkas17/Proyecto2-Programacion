<%-- 
    Document   : logout
    Created on : 13/06/2021, 05:16:30 AM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <%
            session = request.getSession();
            session.removeAttribute("id_usuario");
            session.removeAttribute("rol");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        %>
    </body>
</html>

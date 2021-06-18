<%-- 
    Document   : compraTiquetes
    Created on : 16/06/2021, 03:47:10 AM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <script src="js/scripts.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="bodyC">
        <jsp:directive.include file="headerSesionActivaAdmin.jsp" />
        <%
            String pelicula = request.getParameter("pelicula");
            String fecha = request.getParameter("fecha");
            String numero = request.getParameter("numero");
        %>
        <p><%=pelicula%></p>
        <p><%=fecha%></p>
        <p><%=numero%></p>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>

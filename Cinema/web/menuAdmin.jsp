<%-- 
    Document   : menuAdmin
    Created on : 13/06/2021, 05:23:46 AM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <script src="js/scripts.js" type="text/javascript"></script>
        <script src="js/getJSON.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: #333333;">
        <jsp:directive.include file="headerSesionActivaAdmin.jsp" />
        <jsp:directive.include file="headerAnuncio.jsp" />
        <div id="wrapper">
            <div id="contents">
                <a href="registroPelicula.jsp">  
                    <button type="submit">Registro Pelicula</button>  
                </a>
                <a href="registroSala.jsp">  
                    <button type="submit">Registro Sala</button>  
                </a>
                <a href="registrarFuncion.jsp">  
                    <button type="submit">Registrar Funcion</button>  
                </a>
                <a href="tablaFacturas.jsp">  
                    <button type="submit">Facturas Emitidas</button>  
                </a>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
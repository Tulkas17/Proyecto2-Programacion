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
            <div id="wrapper">
                <div id="contents">
                    <form class="formPelicula">
                        <h1>Registro de peliculas</h1>
                        <p style="color: white;font-size: 17px;opacity: 50%;text-align: center;">
                            (Ingresar codigo de provisto por www.themoviedb.org)</p>
                        <label style="color:white;">Codigo:  </label>
                        <input type="text" id="codigoPelicula" name="codigoPelicula"><br><br>
                        <button style="margin-left: 50%;margin-right: 50%;" class="botonPeli" type="button" onclick="registrarPelicula();">Registrar</button>
                    </form>
                </div>
            </div>
            <div>
                <jsp:directive.include file="registroSala.jsp" />
            </div>
            <div>
                <jsp:directive.include file="registrarFuncion.jsp" />
            </div>
            <div>
                <jsp:directive.include file="tablaFacturas.jsp" />
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>
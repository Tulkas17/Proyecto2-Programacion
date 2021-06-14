<%-- 
    Document   : login
    Created on : 13/06/2021, 01:28:56 AM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <script src="js/getJSON.js" type="text/javascript"></script>
        <script src="js/inicioSesion.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body style="background-color: #333333;" >
        <jsp:directive.include file="header.jsp" />
        <jsp:directive.include file="headerAnuncio.jsp" />
        <div id="wrapper">
            <div id="contents">
                <form class="formLogin" style="color: white;">
                    <table>
                        <tr>
                            <td class="etiqueta">
                                <label for="usuario">Usuario:&nbsp;</label>
                            </td>
                            <td class="campo">
                                <input type="text" size="30"
                                       id="usuario" name="usuario" autocomplete="off" />
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta">
                                <label for="password">Clave:&nbsp;</label>
                            </td>
                            <td class="campo">
                                <input type="password" size="30"
                                       id="password" name="password" autocomplete="off" />
                            </td>
                        </tr>
                        <tr>
                            <td class="controles" colspan="2">
                                <button type="button" onclick="verificarUsuario();">Verificar</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="ocultar" style="display: none">
            <div id="wrapper">
                <div id="contents">
                    <p id="advertencia" style="color: blue;"></p>
                    
                </div>
                <button id="botonRegistro" type="button" onclick="registrar()" style="margin-left: 45%; margin-right: 50%;">Registrar</button>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>

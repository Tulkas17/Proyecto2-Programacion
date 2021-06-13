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
        <script src="js/getJSON.js" type="text/javascript"></script>
        <script src="js/inicioSesion.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:directive.include file="header.jsp" />
        <form class="formLogin">
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
                        <button type="button" onclick="verificarUsuario();">Varificar</button>
                    </td>
                </tr>
            </table>
            <div>
                <p id="advertencia"></p>
                <button id="botonRegistro" style="display:none" type="button" onclick="ocultar()">Ocultar</button>
            </div>
        </form>
    </body>
</html>

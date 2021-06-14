<%-- 
    Document   : registro
    Created on : 07/06/2021, 04:13:38 AM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <script src="js/getJSON.js" type="text/javascript"></script>
        <script src="js/registroUsuario.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body style="background-color: #333333;">
        <jsp:directive.include file="header.jsp" />
        <jsp:directive.include file="headerAnuncio.jsp" />
        <section>
            <div id="wrapper">
                <div id ="contents">
                    <form class="formLogin" name="nuevoUsuario" id="nuevoUsuario" style="color: white;">
                        <table>
                            <tr>
                                <td class="etiqueta">
                                    <label for="id_cliente">ID del usuario (<em>login</em>):&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="text" size="10"
                                           id="id_cliente" name="id_cliente" autocomplete="off" />
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta">
                                    <label for="clave">Clave de acceso:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="password" size="30"
                                           id="clave" name="clave" />
                            </tr>
                            <tr>
                                <td class="etiqueta">
                                    <label for="apellidos">Apellidos:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="text" size="30"
                                           id="apellidos" name="apellidos" autocomplete="off" />
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta">
                                    <label for="apellidos">Nombre:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="text" size="15"
                                           id="nombre" name="nombre" autocomplete="off" />
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta">
                                    <label for="telefono">Telefono:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="text" size="8"
                                           id="telefono" name="telefono" autocomplete="off" />
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta">
                                    <label for="tarjeta_pago">Numero de tarjeta (<em>Pago</em>):&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="text" size="16"
                                           id="tarjeta_pago" name="tarjeta_pago" autocomplete="off" />
                                </td>
                            </tr>
                            <tr>
                                <td class="controles" colspan="2">
                                    <button type="button" onclick="registrarUsuario();">Registrar</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </section>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>

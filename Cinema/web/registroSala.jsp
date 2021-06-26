<%-- 
    Document   : registroSala
    Created on : 15/06/2021, 05:33:35 PM
    Author     : Gaby
--%>
<%@page import="servicios.ServicioSala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" value="no-cache, no-store, must-revalidate"/>
        <meta http-equiv="expires" content="0">
        <meta charset="UTF-8" />
        <script src="js/getJSON.js" type="text/javascript"></script>
        
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <script src="js/registroSala.js" type="text/javascript"></script>
      
        <title>Registro de Salas</title>
         <script type="text/javascript">
            inicializarDatos(<%= new ServicioSala().listaCinesJSON()%>);
        </script>
    </head>
    <body onload="initSala();">
        <jsp:directive.include file="headerSesionActivaAdmin.jsp" />
        <jsp:directive.include file="headerAnuncio.jsp" />
        <section>
            <div id="wrapper">
                <div id ="contents">
                    <form class="formSala" name="nuevaSala" id="nuevaSala" style="color: white;">
                        <table>
 
                            <tr>
                                <td class="etiqueta">
                                    <label for="NumeroSala">Numero de Sala:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="numero" size="15"
                                           id="numero" name="numero" />
                            </tr>
                            
                            <tr>
                                <td class="etiqueta">
                                    <label for="capacidad">Capacidad:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <input type="text" size="15"
                                           id="capacidad" name="capacidad" autocomplete="off" />
                                </td>
                            </tr>
                            <tr>
                                 <td class="etiqueta">
                                    <label for="cine">Cine :&nbsp;</label>
                                </td>
                                   <td>
                                    <select id="menuCines" name="menuCines"
                                            onchange="seleccionarCine();">
                                    </select>
                                </td>
                            </tr>
                         
                           
                            <tr>
                                <td class="controles" colspan="2">
                                    <button type="button" onclick="registrarSala();">Registrar Sala</button>
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

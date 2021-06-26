<%-- 
    Document   : registrarFuncion
    Created on : 22/06/2021, 03:33:35 PM
    Author     : Gaby
--%>
<%@page import="servicios.ServicioRegistroFuncion"%>
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
        <script src="js/registroFuncion.js" type="text/javascript"></script>

        <title>Registro de Salas</title>
        <script type="text/javascript">
            inicializarDatos(<%= new ServicioRegistroFuncion().listaSalasJSON()%>,<%= new ServicioRegistroFuncion().listaPeliculasJSON()%>);
        </script>
    </head>
    <body onload="init();">
        <section>
            <div id="wrapper">
                <div id ="contents">
                    <form class="formSala" name="nuevaFuncion" id="nuevaFuncion" style="color: white;">
                        <table>

                            <tr>
                                <td class="etiqueta">
                                    <label for="Fecha">Fecha de funcion PRUEBA:&nbsp;</label>
                                </td>
                                <td class="campo">

                                    <input type="datetime-local" id="date" name="date">
                                  

                            </tr>
                            <tr>
                                <td class="etiqueta">
                                    <label for="sala">Pelicula :&nbsp;</label>
                                </td>
                                <td>
                                    <select id="menuPeliculas" name="menuPeliculas"
                                            onchange="seleccionarPelicula();">
                                    </select>
                                </td>
                            </tr>



                            <tr>
                                <td class="etiqueta">
                                    <label for="sala">Sala :&nbsp;</label>
                                </td>
                                <td>
                                    <select id="menuSalas" name="menuSalas"
                                            onchange="seleccionarSala();">
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td class="controles" colspan="2">
                                    <button type="button" onclick="registrarFuncion();">Registrar Funcion</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
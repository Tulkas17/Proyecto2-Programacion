<%-- 
    Document   : index
    Created on : Jun 5, 2021, 6:02:42 PM
    Author     : roger
--%>

<%@ page import='modelo.Usuario' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" value="no-cache, no-store, must-revalidate"/>
        <meta http-equiv="expires" content="0">
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="css/defaultIndex.css" type="text/css">
        <script src="js/getJSON.js" type="text/javascript"></script>
        <script src="js/scripts.js" type="text/javascript"></script>
        <script src="js/registroUsuario.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body onload="init();" class="bodyC">
        <%
            HttpSession sesionActual = request.getSession(true);
            Usuario usuario = (Usuario)sesionActual.getAttribute("usuario");
            
            if (usuario == null) {%>
            <jsp:directive.include file="header.jsp" />
        <%}else if (usuario != null) { 
           if(usuario.getRol() == 1){%>
            <jsp:directive.include file="headerSesionActivaAdmin.jsp" />
        <%} else if (usuario.getRol() == 2) {%>
            <jsp:directive.include file="headerSesionActiva.jsp" />
        <%}}%>

        

        <jsp:directive.include file="headerAnuncio.jsp" />
        <section>
            <div class="wrapperC">
                <div class="contentsC">
                    <section>
                        <div id="cartelera">
                            <h1>
                                Cartelera de Cine
                            </h1>
                        </div>
                        <table id="galeria" class="galeria">
                            <thead></thead>
                            <tbody>
                            <script type="text/javascript">
                                var m = dimensionTabla.filas;
                                var n = dimensionTabla.columnas;
                                var t = "";
                                for (let i = 0; i < m; i++) {
                                    t += "<tr>";
                                    for (let j = 0; j < n; j++) {
                                        t += "<td>";
                                        t += "<p></p>";
                                        t += "<img />";
                                        t += "<div>";
                                        t += "<ul></ul>";
                                        t += "</div>";
                                        t += "</td>";
                                    }
                                    t += "</tr>";
                                }
                                document.write(t);
                            </script>
                            </tbody>
                            <tfoot></tfoot>
                        </table>
                    </section>
                </div> 
                <jsp:directive.include file="footer.jsp" />
                </body>
                </html>

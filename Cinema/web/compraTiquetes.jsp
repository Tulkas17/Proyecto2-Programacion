<%-- 
    Document   : compraTiquetes
    Created on : 16/06/2021, 03:47:10 AM
    Author     : oscar
--%>
<%@ page import='modelo.Usuario' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <script src="js/scripts.js" type="text/javascript"></script>
        <link href="css/defaultaSIENTO.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/dayjs.min.js" type="text/javascript"></script>
        <script src="js/getJSON.js" type="text/javascript"></script>
        <script src="js/tablero.js" type="text/javascript"></script>
        <script src="js/asientos.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String pelicula = request.getParameter("pelicula");
            String fecha = request.getParameter("fecha");
            String numero = request.getParameter("numero");
            String sala_cinema_id = request.getParameter("sala_cinema_id");
            String sala_cinema_direccion = request.getParameter("sala_cinema_direccion");
        %>
        <script>
            myStorage.setItem('pelicula', "<%=pelicula%>");
            myStorage.setItem('fecha', "<%=fecha%>");
            myStorage.setItem('numero', "<%=numero%>");
            myStorage.setItem('sala_cinema_id', "<%=sala_cinema_id%>");
            myStorage.setItem('sala_cinema_direccion', "<%=sala_cinema_direccion%>");
        </script>
        <%
            HttpSession sesionActual = request.getSession(true);
            Usuario usuario = (Usuario)sesionActual.getAttribute("usuario");
            String id_cliente = "noUsuario";
            int id_rol = 0;
            if(usuario != null){
            id_cliente = usuario.getId_usuario();
            id_rol = usuario.getRol();
            }
            if (usuario == null) {%>
            <jsp:directive.include file="header.jsp" />
        <%}else if (usuario != null) { 
           if(usuario.getRol() == 1){%>
            <jsp:directive.include file="headerSesionActivaAdmin.jsp" />
        <%} else if (usuario.getRol() == 2) {%>
            <jsp:directive.include file="headerSesionActiva.jsp" />
        <%}}%>
        <jsp:directive.include file="headerAnuncio.jsp" />
        <div id="wrapper">
            <header></header>
            <table style="color: white;" >
                <tbody>
                    <tr>
                        <td class="etiqueta">Cinema id:&nbsp;<%=sala_cinema_id%></td>                                
                    </tr>
                    <tr>
                        <td class="etiqueta">Cinema direccion:&nbsp;<%=sala_cinema_direccion%></td>                                
                    </tr>
                    <tr>
                        <td class="etiqueta">Numero de sala:&nbsp;<%=numero%></td>
                    </tr> 
                    <tr>
                        <td class="etiqueta">Fecha de funcion:&nbsp;<%=fecha%></td>
                    </tr>
                </tbody>
            </table>
            <div id="contents">
                <ul style="color: white;"class="showcase">
                    <li>
                        <div id="seat" class="seat" style="background-color: #444451;"></div>
                        <small class="status" style="font-size: 1em;">N/A</small>
                    </li>
                    <li>
                        <div id="seat" class="seat selected" style="background-color: #6feaf6;"></div>
                        <small class="status" style="font-size: 1em;">Selected</small>
                    </li>
                    <li>
                        <div id="seat" class="seat occupied"></div>
                        <small class="status" style="font-size: 1em;">Occupied</small>
                    </li>
                    <li>
                        <div >PRECIO POR BUTACA:₡3500 </div>
                    </li>
                </ul>
                    <section id='seccionTabla'>
                    </section>
                </div>
            <div id="contents">
                <%


                    if (usuario != null) {
                        out.print("<p style='color: white;'>Usuario:&nbsp;<strong>");
                        out.print(usuario.getId_usuario());
                        out.println("</strong></p>");
                    } else {
                        out.print("<p style='color: red;'>Usuario:&nbsp;<strong>");
                        out.println("(No se ha iniciado la sesión.)");
                        out.println("</strong></p>");
                %>
                
                <%   }
                %>


                <section> 
                    <hr />
                    <button type="button" onclick="recargarAsientos();">Borrar Selecionados</button>
                    <button type="button" onclick="comprar('<%=fecha%>','<%=pelicula%>','<%=numero%>','<%=sala_cinema_id%>','<%=id_cliente%>','<%=id_rol%>');" >Comprar</button>  
                    <%
                        if (usuario != null) {
                        } else { %>                        
                    <%   }
                    %>

                    <hr />
                </section>
            </div>
        </div>
        <jsp:directive.include file="footer.jsp" />
    </body>
</html>

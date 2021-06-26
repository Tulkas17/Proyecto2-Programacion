<%-- 
    Document   : comprasRealizadas
    Created on : 25/06/2021, 10:00:11 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <link rel="stylesheet" href="css/tablas.css" type="text/css">
        <script src="js/scripts.js" type="text/javascript"></script>
        <script src="js/TablaHistorial.js" type="text/javascript"></script>
        <script src="js/getJSON.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:directive.include file="headerSesionActiva.jsp" />
         <jsp:directive.include file="headerAnuncio.jsp" />
         <div id="wrapper">
             <div id="contents">
                 <div id="contents" >            
                <section id="sec4">   
                    <table class="tablaPoblacion">
                        <caption>Historial de compra</caption>
                        <thead>
                            <tr>
                               <th>
                                #Factura
                            </th>
                            <th>
                                Fecha
                            </th>
                            <th>
                                Cliente_id
                            </th>
                            <th>
                                Total
                            </th>
            
                            </tr>
                        </thead>    
                        <tbody id="bt4"></tbody>
                        
                    </table>                
                </section>
            </div>
             </div>
         </div>
         <jsp:directive.include file="footer.jsp" />
    </body>
</html>

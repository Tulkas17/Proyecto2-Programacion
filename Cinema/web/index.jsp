<%-- 
    Document   : index
    Created on : Jun 5, 2021, 6:02:42 PM
    Author     : roger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" value="no-cache, no-store, must-revalidate"/>
        <meta http-equiv="expires" content="0">
        <meta charset="UTF-8" />
        <script src="js/getJSON.js" type="text/javascript"></script>
        <script src="js/scripts.js" type="text/javascript"></script>
        <script src="js/registroUsuario.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session = request.getSession();
            if (session.getAttribute("id_usuario") != null) {%>
        <jsp:directive.include file="headerSesionActiva.jsp" />
        <%}else {%>
        <jsp:directive.include file="header.jsp" />
        <%}
        %>
        <section>
            <div class="paper">
                <div class="rule_pattern">
                    <div id="sheet">
                        <p id='usuario'></p>
                    </div>
                </div>
            </div>                 
        </section>
    </body>
</html>

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author oscar
 */
@WebServlet(name = "ServicioRegistro", urlPatterns = {"/ServicioRegistro"})
@MultipartConfig
public class ServicioRegistro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            StringBuilder r = new StringBuilder();
            JSONObject resultado = new JSONObject();
            
            try {

            //UsuarioDao daoUser = new UsuarioDaoImpl();
            /*JSONObject obj = new JSONObject(toUTF8String(request.getParameter("usuario")));

            aux.setId_usuario(obj.getString("id_usuario"));
            aux.setClave(obj.getString("clave"));
            aux.setRol(obj.getInt("rol"));
            
            System.out.printf("     : '%s'%n", aux.toString());*/
            //daoUser.save(aux);

            JSONObject obj = new JSONObject(toUTF8String(request.getParameter("user")));
            Usuario u = new Usuario(
                    obj.getString("id_usuario"),
                    obj.getString("clave"),
                    obj.getInt("rol")
            );
            System.out.printf("     : '%s'%n", u);

            resultado.put("result", "ok");

            } catch (JsonSyntaxException
                    | UnsupportedEncodingException
                    | NumberFormatException
                    | JSONException ex) {

                resultado.put("result", "exception");
                resultado.put("message", ex.getMessage());
            }

            out.println(resultado);

        } 

    }

    private String toUTF8String(String s) throws UnsupportedEncodingException {
        return encoding.isPresent() ? s : new String(s.getBytes(), StandardCharsets.UTF_8);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private Optional<String> encoding;
}

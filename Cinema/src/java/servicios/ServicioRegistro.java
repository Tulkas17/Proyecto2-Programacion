/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dao.ClienteDaoImpl;
import dao.ClienteDao;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
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
            throws ServletException, IOException, SQLException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            StringBuilder r = new StringBuilder();
            JSONObject resultado = new JSONObject();
            
            try {
                
                UsuarioDao userDao = new UsuarioDaoImpl();
                ClienteDao cliDao = new ClienteDaoImpl();

            JSONObject obj = new JSONObject(toUTF8String(request.getParameter("user")));
            Usuario u = new Usuario(
                    obj.getString("id_cliente"),
                    obj.getString("clave"),
                    2
            );
            
            Cliente c = new Cliente(
                    obj.getString("id_cliente"),
                    obj.getString("apellidos"),
                    obj.getString("nombre"),
                    obj.getString("telefono"),
                    obj.getString("tarjeta_pago"),
                    u
            );
            
            userDao.save(u);
            cliDao.save(c);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Optional<String> encoding;
}

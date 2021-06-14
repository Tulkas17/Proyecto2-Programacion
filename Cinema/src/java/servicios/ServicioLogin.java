/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.JsonSyntaxException;
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
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author oscar
 */
@WebServlet(name = "ServicioLogin", urlPatterns = {"/ServicioLogin"})
@MultipartConfig
public class ServicioLogin extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resultado = new JSONObject();

        try (PrintWriter out = response.getWriter()) {
            StringBuilder r = new StringBuilder();

            try {

                UsuarioDao userDao = new UsuarioDaoImpl();

                JSONObject obj = new JSONObject(toUTF8String(request.getParameter("user")));
                
                Usuario u = new Usuario(
                        obj.getString("id_cliente"),
                        obj.getString("clave"),
                        2
                );

                int respuesta = userDao.validar(u);
                
                if(respuesta == 1){
                    u = userDao.findById(obj.getString("id_cliente"));
                    HttpSession session = request.getSession();
                    session.setAttribute("id_usuario", u.getId_usuario());
                    session.setAttribute("rol", u.getRol());
                    resultado.put("result", "ok");
                    //resultado.put("id_usuario", u.getId_usuario());
                    resultado.put("rol", u.getRol());
                }
                else{
                    resultado.put("result", "bad");
                }

            } catch (JsonSyntaxException
                    | UnsupportedEncodingException
                    | NumberFormatException
                    | JSONException ex) {
            }
            out.println(resultado);
        }

    }
    
    private String toUTF8String(String s) throws UnsupportedEncodingException {
        return encoding.isPresent() ? s : new String(s.getBytes(), StandardCharsets.UTF_8);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private Optional<String> encoding;

}

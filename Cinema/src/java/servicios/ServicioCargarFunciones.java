/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.JsonSyntaxException;
import dao.FuncionDao;
import dao.FuncionDaoImpl;
import dao.PeliculaDao;
import dao.PeliculaDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Funcion;
import modelo.Pelicula;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author oscar
 */
@WebServlet(name = "ServicioCargarFunciones", urlPatterns = {"/ServicioCargarFunciones"})
public class ServicioCargarFunciones extends HttpServlet {

    private Optional<String> encoding;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try {

                FuncionDao funcDao = new FuncionDaoImpl();
                List<Funcion> funciones = funcDao.findAll();

                JSONObject o = new JSONObject();
                JSONObject e = null;
                JSONArray r = new JSONArray();

                for (int i = 0; i < funciones.size(); i++) {
                    e = new JSONObject();
                    e.put("sala_cinema_id", funciones.get(i).getSala_cinema_id());
                    e.put("sala_numero", funciones.get(i).getSala_numero());
                    e.put("fecha", funciones.get(i).getFecha());
                    e.put("pelicula_id", funciones.get(i).getPelicula_id());
                    r.put(e);
                }

                o.put("datos_funciones", r);
                out.println(o);

            } catch (JsonSyntaxException
                    | NumberFormatException
                    | JSONException ex) {
            }

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
            Logger.getLogger(ServicioCargarFunciones.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioCargarFunciones.class.getName()).log(Level.SEVERE, null, ex);
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

}

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
import dao.SalaDao;
import dao.SalaDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Funcion;
import modelo.ListaPeliculas;
import modelo.ListaSalas;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gaby
 */
@WebServlet(name = "ServicioRegistroFuncion", urlPatterns = {"/ServicioRegistroFuncion"})
@MultipartConfig
public class ServicioRegistroFuncion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resultado = new JSONObject();

        try (PrintWriter out = response.getWriter()) {

            try {

                FuncionDao funcionDao = new FuncionDaoImpl();
                SalaDao salaDao = new SalaDaoImpl();
                PeliculaDao peliDao = new PeliculaDaoImpl();
                JSONObject obj = new JSONObject(toUTF8String(request.getParameter("funcion")));

                String salaNumero = obj.getString("sala_numero");
                String peliId = obj.getString("pelicula");
                String fecha = obj.getString("fecha");
                String str[] = peliId.split("-");
                String str1 = str[0];

                int numeroSala = Integer.parseInt(salaNumero);
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date parsedDate = inputFormat.parse(fecha);
                parsedDate.setSeconds(00);
                String formattedDate = outputFormat.format(parsedDate);

                Funcion funcion = new Funcion();
                funcion.setPelicula(peliDao.findById(str1));
                funcion.setPelicula_id(str1);
                funcion.setSala_cinema_id(salaDao.findById(numeroSala).getCine().getId_cinema());
                funcion.setFecha(formattedDate);
                funcion.setSala_numero(numeroSala);
                funcionDao.save(funcion);
            } catch (JsonSyntaxException
                    | UnsupportedEncodingException
                    | NumberFormatException
                    | JSONException ex) {
            } catch (ParseException ex) {
                Logger.getLogger(ServicioRegistroFuncion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            Logger.getLogger(ServicioRegistroFuncion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioRegistroPelicula.class.getName()).log(Level.SEVERE, null, ex);
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

    public String listaSalasJSON() {
        try {
            SalaDao salaDao = new SalaDaoImpl();
            return new ListaSalas(salaDao.findAll()).toString();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioRegistroFuncion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 

    public String listaPeliculasJSON()  {
        try {
            PeliculaDao peliculaDao = new PeliculaDaoImpl();
            return new ListaPeliculas(peliculaDao.findAll()).toString();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioRegistroFuncion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String toUTF8String(String s) throws UnsupportedEncodingException {
        return encoding.isPresent() ? s : new String(s.getBytes(), StandardCharsets.UTF_8);
    }

    private Optional<String> encoding;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.CineDao;
import dao.CineDaoImpl;
import dao.SalaDao;
import dao.SalaDaoImpl;
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
import modelo.Cine;
import org.json.JSONObject;
import modelo.ListaCines;
import modelo.Sala;

/**
 *
 * @author Gaby
 */
@WebServlet(name = "ServicioSala", urlPatterns = {"/ServicioSala"})
@MultipartConfig
public class ServicioSala extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
                 StringBuilder r = new StringBuilder();
            try {
                
                //out.println(listaCinesJSON());
                
                 CineDao cineDao = new CineDaoImpl();
                SalaDao salaDao = new SalaDaoImpl();

                JSONObject obj = new JSONObject(toUTF8String(request.getParameter("sala")));
               
                //CINE
               String idCine = obj.getString("cinema_id");
               int idCinema = Integer.parseInt(idCine);
               Cine cineAux = cineDao.findById(idCinema);
               //SALA
               String numeroAux = obj.getString("numero");
               String capacidadAux = obj.getString("capacidad");
               int numero = Integer.parseInt(numeroAux);
               int capacidad = Integer.parseInt(capacidadAux);
               Sala sala = new Sala (cineAux,numero, capacidad );
               salaDao.save(sala);

                
            } catch (Exception ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                out.println(new JSONObject());
            }
        }
    }


    public String listaCinesJSON() throws Exception {
         CineDao cineDao = new CineDaoImpl();
        return new ListaCines(cineDao.findAll()).toString();
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

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
      private Optional<String> encoding;
}

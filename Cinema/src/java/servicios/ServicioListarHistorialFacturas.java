/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.JsonSyntaxException;
import dao.CineDao;
import dao.CineDaoImpl;
import dao.FacturaDao;
import dao.FacturaDaoImpl;
import dao.FuncionDao;
import dao.FuncionDaoImpl;
import dao.TiqueteDao;
import dao.TiqueteDaoImpl;
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
import javax.servlet.http.HttpSession;
import modelo.Cine;
import modelo.Factura;
import modelo.Funcion;
import modelo.Tiquete;
import modelo.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author oscar
 */
@WebServlet(name = "ServicioListarHistorialFacturas", urlPatterns = {"/ServicioListarHistorialFacturas"})
public class ServicioListarHistorialFacturas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Optional<String> encoding;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try {

                HttpSession sesionActual = request.getSession(true);
                Usuario usuario = (Usuario)sesionActual.getAttribute("usuario");
                TiqueteDao tiqDao = new TiqueteDaoImpl();
                
                FacturaDao factDao = new FacturaDaoImpl();
                List<Factura> facturas = factDao.findFacturasByCliente(usuario.getId_usuario());
                List<Tiquete> tiquetes = tiqDao.findAll();

                int contador = 0;
                
                JSONObject o = new JSONObject();
                JSONObject e = null;
                JSONArray r = new JSONArray();

                for (int i = 0; i < facturas.size(); i++) {
                    e = new JSONObject();
                    e.put("seq_factura", facturas.get(i).getSeq_factura());
                    e.put("fecha", facturas.get(i).getFecha());
                    e.put("cliente_id", facturas.get(i).getCliente().getId_cliente());
                    
                    for(int j = 0; j < tiquetes.size();j++){
                        if(tiquetes.get(j).getFactura_seq() == facturas.get(i).getSeq_factura()){
                            contador++;
                        }
                    }
                    
                    e.put("total", contador*3500);
                    r.put(e);
                    contador = 0;
                }

                o.put("datos_Historial", r);
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
            Logger.getLogger(ServicioListarHistorialFacturas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioListarHistorialFacturas.class.getName()).log(Level.SEVERE, null, ex);
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

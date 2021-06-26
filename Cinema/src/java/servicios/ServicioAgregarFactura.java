/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.JsonSyntaxException;
import dao.AsientoFuncionDao;
import dao.AsientoFuncionDaoImpl;
import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.FacturaDao;
import dao.FacturaDaoImpl;
import dao.PeliculaDao;
import dao.PeliculaDaoImpl;
import dao.TiqueteDao;
import dao.TiqueteDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Asiento_funcion;
import modelo.Cliente;
import modelo.Factura;
import modelo.Pelicula;
import modelo.Tiquete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author oscar
 */
@WebServlet(name = "ServicioAgregarFactura", urlPatterns = {"/ServicioAgregarFactura"})
@MultipartConfig
public class ServicioAgregarFactura extends HttpServlet {

    private Optional<String> encoding;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resultado = new JSONObject();

        try (PrintWriter out = response.getWriter()) {
            StringBuilder r = new StringBuilder();

            try {
                JSONObject obj = new JSONObject(toUTF8String(request.getParameter("factura")));

                AsientoFuncionDao asiDao = new AsientoFuncionDaoImpl();
                TiqueteDao tiqDao = new TiqueteDaoImpl();
                FacturaDao facDao = new FacturaDaoImpl();
                ClienteDao cliDao = new ClienteDaoImpl();
                List<Tiquete> listaTiquetes = tiqDao.findAll();
                List<Factura> listaFacturas = facDao.findAll();
                int id_factura = listaFacturas.size() + 1;
                int id_tiquete_inicial = listaTiquetes.size() + 1;
                JSONObject jsonAux = new JSONObject();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                String dateInString = "";
                Date date = new Date();
                char charAux;
                String stringAux;

                JSONArray asientosFuncion = obj.getJSONArray("posiciones");

                Asiento_funcion asientoAux = new Asiento_funcion();
                Tiquete tiqueteAux = new Tiquete();

                java.sql.Timestamp dateTime = null;

                Factura factura = new Factura();
                factura.setSeq_factura(id_factura);
                String id_cliente = obj.getString("id_cliente");
                Cliente clienteAux = cliDao.findById(id_cliente);
                factura.setCliente(clienteAux);
                factura.setTarjeta_pago(clienteAux.getTarjeta_pago());

                

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                dateTime = java.sql.Timestamp.valueOf((String) obj.get("fecha"));

                factura.setFecha(formattedDate);

                facDao.save(factura);

                for (int i = 0; i < asientosFuncion.length(); i++) {
                    asientoAux.setFuncion_sala_cinema_id(obj.getInt("sala_cinema_id"));
                    asientoAux.setFuncion_sala_numero(obj.getInt("numero"));
                    jsonAux = (JSONObject) asientosFuncion.get(i);
                    dateTime = java.sql.Timestamp.valueOf((String) obj.get("fecha"));
                    //date = formatter.parse(dateInString);
                    asientoAux.setFuncion_fecha((String) obj.get("fecha"));
                    asientoAux.setOcupado(true);
                    stringAux = (String) jsonAux.get("fila");
                    charAux = stringAux.charAt(0);
                    asientoAux.setFila(charAux);
                    asientoAux.setPosicion(jsonAux.getInt("columna"));

                    tiqueteAux.setMonto(3500);
                    tiqueteAux.setId_tiquete(id_tiquete_inicial);
                    tiqueteAux.setFactura_seq(id_factura);
                    tiqueteAux.setAsiento_funcion_sala_numero(asientoAux.getFuncion_sala_numero());
                    tiqueteAux.setAsiento_funcion_sala_cinema(asientoAux.getFuncion_sala_cinema_id());
                    tiqueteAux.setAsiento_funcion_posicion(asientoAux.getPosicion());
                    tiqueteAux.setAsiento_funcion_fila(asientoAux.getFila());
                    dateTime = java.sql.Timestamp.valueOf((String) obj.get("fecha"));
                    tiqueteAux.setAsiento_funcion_fecha((String) obj.get("fecha"));

                    asiDao.save(asientoAux);
                    tiqDao.save(tiqueteAux);
                    id_tiquete_inicial++;
                }

                resultado.put("result", "ok");

            } catch (JsonSyntaxException
                    | UnsupportedEncodingException
                    | NumberFormatException
                    | JSONException ex) {
            }
            out.println(resultado);
        }
    }

    private String obtenerFecha(Date date) {
        String anno = date.getYear() + "";
        String mes = date.getMonth() + "";
        String dia = date.getDay() + "";
        String hora = date.getHours() + "";
        String minuto = date.getMinutes() + "";
        String segundos = date.getSeconds() + "";

        return anno + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundos;
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
            Logger.getLogger(ServicioAgregarFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServicioAgregarFactura.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioAgregarFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServicioAgregarFactura.class.getName()).log(Level.SEVERE, null, ex);
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

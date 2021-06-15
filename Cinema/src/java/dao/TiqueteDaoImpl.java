/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Asiento_funcion;
import modelo.Factura;
import modelo.Sala;
import modelo.Tiquete;

/**
 *
 * @author Gaby
 */
public class TiqueteDaoImpl implements TiqueteDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;
    private AsientoFuncionDao asientoFuDao;
    private FacturaDao factuDao;

    public TiqueteDaoImpl() throws SQLException {
        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
        this.asientoFuDao = new AsientoFuncionDaoImpl();
        this.factuDao = new FacturaDaoImpl();
    }

    public List<Tiquete> findTiqueteByFactura(int factura_seq) throws SQLException {
        List<Tiquete> tiqueteList = null;
        String selectTableSQL = "Select ti.* from bd_cine.tiquete ti inner "
                + " join bd_cine.asiento_funcion asi on ti.asiento_funcion_fila = asi.fila"
                + " and ti.asiento_funcion_posicion = asi.posicion inner join bd_cine.factura fac "
                + " on ti.factura_seq = fac.seq_factura where ti.factura_seq = ?;";
        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, factura_seq);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                tiqueteList = new ArrayList<>();
                while (rs.next()) {
                    Tiquete tiquete = new Tiquete();
                    tiquete.setAsientoFuncion(this.asientoFuDao.
                            findByFilaPosicion(rs.getString("asiento_funcion_fila").charAt(0), rs.getInt("asiento_funcion_posicion")));
                    tiquete.setAsiento_funcion_fila(rs.getString("asiento_funcion_fila").charAt(0));
                    tiquete.setAsiento_funcion_posicion(rs.getInt("asiento_funcion_posicion"));
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("asiento_funcion_fecha"));
                    tiquete.setAsiento_funcion_fecha(dateTime);
                    tiquete.setAsiento_funcion_sala_cinema(rs.getInt("asiento_funcion_sala_cinema_id"));
                    tiquete.setAsiento_funcion_sala_numero(rs.getInt("asiento_funcion_sala_numero"));
                    tiquete.setId_tiquete(rs.getInt("id_tiquete"));
                    tiquete.setFactura_seq(rs.getInt("factura_seq"));
                    tiquete.setMonto(rs.getDouble("monto"));
                    tiquete.setFactura(this.factuDao.findById(rs.getInt("factura_seq")));

                    tiqueteList.add(tiquete);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding tiquete ", e);
        }
        return tiqueteList;
    }

    public Tiquete findTiqueteById(int id_tiquete) throws SQLException {
        Tiquete tiquete = null;
        String selectTableSQL = "Select ti.* from bd_cine.tiquete ti inner "
                + " join bd_cine.asiento_funcion asi on ti.asiento_funcion_fila = asi.fila"
                + " and ti.asiento_funcion_posicion = asi.posicion inner join bd_cine.factura fac "
                + " on ti.factura_seq = fac.seq_factura where ti.id_tiquete = ?;";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, id_tiquete);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                tiquete = new Tiquete();
                while (rs.next()) {

                    tiquete.setAsientoFuncion(this.asientoFuDao.
                            findByFilaPosicion(rs.getString("asiento_funcion_fila").charAt(0), rs.getInt("asiento_funcion_posicion")));
                    tiquete.setAsiento_funcion_fila(rs.getString("asiento_funcion_fila").charAt(0));
                    tiquete.setAsiento_funcion_posicion(rs.getInt("asiento_funcion_posicion"));
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("asiento_funcion_fecha"));
                    tiquete.setAsiento_funcion_fecha(dateTime);
                    tiquete.setAsiento_funcion_sala_cinema(rs.getInt("asiento_funcion_sala_cinema_id"));
                    tiquete.setAsiento_funcion_sala_numero(rs.getInt("asiento_funcion_sala_numero"));
                    tiquete.setId_tiquete(rs.getInt("id_tiquete"));
                    tiquete.setFactura_seq(rs.getInt("factura_seq"));
                    tiquete.setMonto(rs.getDouble("monto"));
                    tiquete.setFactura(this.factuDao.findById(rs.getInt("factura_seq")));

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  tiquete ", e);
        }
        return tiquete;
    }

    public List<Tiquete> findAll() throws SQLException {
        List<Tiquete> tiqueteList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.tiquete;";

        try {

            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                tiqueteList = new ArrayList<>();
                while (rs.next()) {
                    Tiquete tiquete = new Tiquete();
                    tiquete.setAsientoFuncion(this.asientoFuDao.
                            findByFilaPosicion(rs.getString("asiento_funcion_fila").charAt(0), rs.getInt("asiento_funcion_posicion")));
                    tiquete.setAsiento_funcion_fila(rs.getString("asiento_funcion_fila").charAt(0));
                    tiquete.setAsiento_funcion_posicion(rs.getInt("asiento_funcion_posicion"));
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("asiento_funcion_fecha"));
                    tiquete.setAsiento_funcion_fecha(dateTime);
                    tiquete.setAsiento_funcion_sala_cinema(rs.getInt("asiento_funcion_sala_cinema_id"));
                    tiquete.setAsiento_funcion_sala_numero(rs.getInt("asiento_funcion_sala_numero"));
                    tiquete.setId_tiquete(rs.getInt("id_tiquete"));
                    tiquete.setFactura_seq(rs.getInt("factura_seq"));
                    tiquete.setMonto(rs.getDouble("monto"));
                    tiquete.setFactura(this.factuDao.findById(rs.getInt("factura_seq")));

                    tiqueteList.add(tiquete);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding tiquete ", e);
        }
        return tiqueteList;
    }

    @Override
    public Tiquete save(Tiquete tiquete) throws SQLException {

 String sql = "INSERT INTO bd_cine.tiquete (id_tiquete, factura_seq, asiento_funcion_sala_cinema_id, asiento_funcion_sala_numero,"
                + " asiento_funcion_fecha, asiento_funcion_fila, asiento_funcion_posicion, monto) "
         + "VALUES ("+tiquete.getId_tiquete()+", "+tiquete.getFactura_seq()
         +", "+tiquete.getAsiento_funcion_sala_cinema()+", "+tiquete.getAsiento_funcion_sala_numero()+
         ", '"+tiquete.getAsiento_funcion_fecha()+"', '"+tiquete.getAsiento_funcion_fila()+"', "
         +tiquete.getAsiento_funcion_posicion()+", "+tiquete.getMonto()+");";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  tiquete ", e);
        }

        return tiquete;
    }

    public Tiquete update(Tiquete tiquete) throws SQLException {
        String sql = "update bd_cine.tiquete set monto= ? where id_tiquete = ?";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setDouble(1, tiquete.getMonto());
            ps.setInt(2, tiquete.getId_tiquete());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update tiquete ", e);
        }
        return tiquete;
    }

    public void delete(Tiquete tiquete) throws SQLException {
        String insertTableSQL = "delete from bd_cine.tiquete where id_tiquete = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setInt(1, tiquete.getId_tiquete());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete tiquete ", e);
        }
    }

  
}

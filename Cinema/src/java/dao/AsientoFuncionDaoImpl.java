/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static java.lang.System.out;
import java.net.Authenticator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

import java.sql.Date;
import java.util.List;
import modelo.Asiento_funcion;

/**
 *
 * @author Gaby
 */
public class AsientoFuncionDaoImpl implements AsientoFuncionDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;
    private SalaDao salaDao;
    private FuncionDao funcionDao;

    public AsientoFuncionDaoImpl() throws SQLException {
        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
        this.salaDao = new SalaDaoImpl();
        this.funcionDao = new FuncionDaoImpl();
    }

    @Override
    public Asiento_funcion save(Asiento_funcion asientoFuncion) throws SQLException {
        String sql = "INSERT INTO bd_cine.asiento_funcion (funcion_sala_cinema_id, funcion_sala_numero, "
                + "funcion_fecha, fila, posicion, ocupado) VALUES "
                + "(" + asientoFuncion.getFuncion_sala_cinema_id() + ", " + asientoFuncion.getFuncion_sala_numero()
                + ", '" + asientoFuncion.getFuncion_fecha() + "', '" + asientoFuncion.getFila() + "', "
                + asientoFuncion.getPosicion() + ", " + asientoFuncion.getOcupado() + ");";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  asiento funcion ", e);
        }
        return asientoFuncion;
    }

    @Override
    public Asiento_funcion findByFilaPosicion(char fila, int posicion) throws SQLException {
        Asiento_funcion asientoFuncion = null;

        String selectTableSQL = "SELECT s.* from bd_cine.asiento_funcion s inner join"
                + " bd_cine.funcion fu on s.funcion_fecha = fu.fecha where s.fila = ? and s.posicion = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);

            ps.setString(1, Character.toString(fila));
            ps.setInt(2, posicion);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                asientoFuncion = new Asiento_funcion();
                while (rs.next()) {

                    asientoFuncion.setFuncion_sala_cinema_id(rs.getInt("funcion_sala_cinema_id"));
                    asientoFuncion.setFuncion_sala_numero(rs.getInt("funcion_sala_numero"));

                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("funcion_fecha"));
                    asientoFuncion.setFuncion_fecha(rs.getString("funcion_fecha"));
                    asientoFuncion.setFuncion(this.funcionDao.findByDate(dateTime));
                    asientoFuncion.setFila(rs.getString("fila").charAt(0));
                    asientoFuncion.setPosicion(rs.getInt("posicion"));
                    asientoFuncion.setOcupado(rs.getBoolean("ocupado"));

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  asiento funcion ", e);
        }
        return asientoFuncion;
    }

    @Override
    public List<Asiento_funcion> findAll() throws SQLException {
        List<Asiento_funcion> asientoFuncionList = null;

        String selectTableSQL = "select * from bd_cine.asiento_funcion;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                asientoFuncionList = new ArrayList<>();
                while (rs.next()) {
                    Asiento_funcion asientoFuncion = new Asiento_funcion();
                    asientoFuncion.setFuncion_sala_cinema_id(rs.getInt("funcion_sala_cinema_id"));
                    asientoFuncion.setFuncion_sala_numero(rs.getInt("funcion_sala_numero"));

                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("funcion_fecha"));
                    asientoFuncion.setFuncion_fecha(rs.getString("funcion_fecha"));
                    asientoFuncion.setFuncion(this.funcionDao.findByDate(dateTime));
                    asientoFuncion.setFila(rs.getString("fila").charAt(0));
                    asientoFuncion.setPosicion(rs.getInt("posicion"));
                    asientoFuncion.setOcupado(rs.getBoolean("ocupado"));
                    asientoFuncionList.add(asientoFuncion);
                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  asiento funcion ", e);
        }
        return asientoFuncionList;
    }

    @Override
    public Asiento_funcion update(Asiento_funcion asientoFuncion) throws SQLException {
        String sql = "update bd_cine.asiento_funcion set  ocupado= ? "
                + "where fila = ? and posicion = ?";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setBoolean(1, asientoFuncion.getOcupado());
            ps.setString(2, Character.toString(asientoFuncion.getFila()));
            ps.setInt(3, asientoFuncion.getPosicion());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update asientoFuncion ", e);
        }
        return asientoFuncion;
    }

    @Override
    public void delete(Asiento_funcion asientoFuncion) throws SQLException {
        String insertTableSQL = "delete from bd_cine.asiento_funcion where fila = ? and posicion = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setString(1, Character.toString(asientoFuncion.getFila()));
            ps.setInt(2, asientoFuncion.getPosicion());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete asiento funcion ", e);
        }
    }

    @Override

    public List<Asiento_funcion> findAsientoByFuncionDate(java.util.Date fechaFuncion) throws SQLException {
        List<Asiento_funcion> asientoFuncionList = null;

        String selectTableSQL = "SELECT s.* from bd_cine.asiento_funcion s inner join"
                + " bd_cine.funcion fu on s.funcion_fecha = fu.fecha where s.funcion_fecha = '" + fechaFuncion + "';";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                asientoFuncionList = new ArrayList<>();
                while (rs.next()) {
                    Asiento_funcion asientoFuncion = new Asiento_funcion();
                    asientoFuncion.setFuncion_sala_cinema_id(rs.getInt("funcion_sala_cinema_id"));
                    asientoFuncion.setFuncion_sala_numero(rs.getInt("funcion_sala_numero"));

                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("funcion_fecha"));
                    asientoFuncion.setFuncion_fecha(rs.getString("funcion_fecha"));
                    asientoFuncion.setFuncion(this.funcionDao.findByDate(dateTime));
                    asientoFuncion.setFila(rs.getString("fila").charAt(0));
                    asientoFuncion.setPosicion(rs.getInt("posicion"));
                    asientoFuncion.setOcupado(rs.getBoolean("ocupado"));
                    asientoFuncionList.add(asientoFuncion);
                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  asiento funcion ", e);
        }
        return asientoFuncionList;
    }

}

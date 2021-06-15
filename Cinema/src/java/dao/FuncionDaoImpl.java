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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Funcion;
import modelo.Pelicula;
import modelo.Sala;

/**
 *
 * @author Gaby
 */
public class FuncionDaoImpl implements FuncionDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;
    private PeliculaDao peliDao;
    private SalaDao salaDao;

    public FuncionDaoImpl() throws SQLException {

        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
        this.peliDao = new PeliculaDaoImpl();
        this.salaDao = new SalaDaoImpl();
    }

    public Funcion findByDate(Date date) throws SQLException {
        Funcion funcion = null;
         String fechaAux ="2021-06-19 10:38:47";
        String selectTableSQL = "select fu.sala_cinema_id, fu.sala_numero, fu.fecha, fu.pelicula_id"
                + " from bd_cine.funcion fu, bd_cine.sala s, bd_cine.pelicula p"
                + " where fu.fecha = '" + date+ "'  and"
                + " s.numero = fu.sala_numero "
                + " and p.id_pelicula = fu.pelicula_id;";
      
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                funcion = new Funcion();
                while (rs.next()) {
                    Pelicula pelicula = this.peliDao.findById(rs.getString("pelicula_id"));
                    Sala sala = this.salaDao.findById(rs.getInt("sala_numero"));
                    funcion.setSala(sala);
                    funcion.setSala_numero(sala.getNumero());
                    funcion.setSala_cinema_id(sala.getCine().getId_cinema());
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    funcion.setFecha(dateTime);
                    funcion.setPelicula(pelicula);
                    funcion.setPelicula_id(pelicula.getId_pelicula());

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  factura ", e);
        }

        return funcion;
    }

    public List<Funcion> findFuncionesByPelicula(String id_pelicula) throws SQLException {
        List<Funcion> funcionList = null;
        String selectTableSQL = "select fu.sala_cinema_id, fu.sala_numero, fu.fecha, fu.pelicula_id"
             + " from bd_cine.funcion fu inner join bd_cine.sala s on s.numero = fu.sala_numero"
            +" inner join bd_cine.pelicula p on p.id_pelicula = fu.pelicula_id"
             +"   where fu.pelicula_id = ?;";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setString(1, id_pelicula);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                funcionList = new ArrayList<>();
                while (rs.next()) {
                    Funcion funcion = new Funcion();
                    Pelicula pelicula = this.peliDao.findById(rs.getString("pelicula_id"));
                    Sala sala = this.salaDao.findById(rs.getInt("sala_numero"));
                    funcion.setSala(sala);
                    funcion.setSala_numero(sala.getNumero());
                    funcion.setSala_cinema_id(sala.getCine().getId_cinema());
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    funcion.setFecha(dateTime);
                    funcion.setPelicula(pelicula);
                    funcion.setPelicula_id(pelicula.getId_pelicula());
                    funcionList.add(funcion);
                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding funcion ", e);
        }
        return funcionList;
    }

    public List<Funcion> findFuncionesByCine(int id_cine) throws SQLException {
        List<Funcion> funcionList = null;
        String selectTableSQL = "select fu.sala_cinema_id, fu.sala_numero, fu.fecha, fu.pelicula_id"
                + "   from bd_cine.funcion fu, bd_cine.sala s, bd_cine.pelicula p"
                + "   where fu.sala_cinema_id= ?  and"
                + "   s.numero = fu.sala_numero "
                + "   and p.id_pelicula = fu.pelicula_id;";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, id_cine);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                funcionList = new ArrayList<>();
                while (rs.next()) {
                    Funcion funcion = new Funcion();
                    Pelicula pelicula = this.peliDao.findById(rs.getString("pelicula_id"));
                    Sala sala = this.salaDao.findById(rs.getInt("sala_numero"));
                    funcion.setSala(sala);
                    funcion.setSala_numero(sala.getNumero());
                    funcion.setSala_cinema_id(sala.getCine().getId_cinema());
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    funcion.setFecha(dateTime);
                    funcion.setPelicula(pelicula);
                    funcion.setPelicula_id(pelicula.getId_pelicula());
                    funcionList.add(funcion);
                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding funcion ", e);
        }
        return funcionList;
    }

    public List<Funcion> findFuncionesBySala(int id_sala) throws SQLException {
        List<Funcion> funcionList = null;
        String selectTableSQL = "select fu.sala_cinema_id, fu.sala_numero, fu.fecha, fu.pelicula_id"
                + "  from bd_cine.funcion fu, bd_cine.sala s, bd_cine.pelicula p"
                + "  where fu.sala_numero= ?  and"
                + "  s.numero = fu.sala_numero "
                + "  and p.id_pelicula = fu.pelicula_id;";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, id_sala);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                funcionList = new ArrayList<>();
                while (rs.next()) {
                    Funcion funcion = new Funcion();
                    Pelicula pelicula = this.peliDao.findById(rs.getString("pelicula_id"));
                    Sala sala = this.salaDao.findById(rs.getInt("sala_numero"));
                    funcion.setSala(sala);
                    funcion.setSala_numero(sala.getNumero());
                    funcion.setSala_cinema_id(sala.getCine().getId_cinema());
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    funcion.setFecha(dateTime);
                    funcion.setPelicula(pelicula);
                    funcion.setPelicula_id(pelicula.getId_pelicula());
                    funcionList.add(funcion);
                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding funcion ", e);
        }
        return funcionList;
    }

    public List<Funcion> findAll() throws SQLException {
        List<Funcion> funcionList = null;
        String selectTableSQL = "select * from bd_cine.funcion;";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                funcionList = new ArrayList<>();
                while (rs.next()) {
                    Funcion funcion = new Funcion();
                    Pelicula pelicula = this.peliDao.findById(rs.getString("pelicula_id"));
                    Sala sala = this.salaDao.findById(rs.getInt("sala_numero"));
                    funcion.setSala(sala);
                    funcion.setSala_numero(sala.getNumero());
                    funcion.setSala_cinema_id(sala.getCine().getId_cinema());
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    funcion.setFecha(dateTime);
                    funcion.setPelicula(pelicula);
                    funcion.setPelicula_id(pelicula.getId_pelicula());
                    funcionList.add(funcion);
                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding funcion ", e);
        }
        return funcionList;
    }

    public Funcion save(Funcion funcion) throws SQLException {
    String fechaAux ="2016-11-19 10:38:47.0";
        String sql = "INSERT INTO bd_cine.funcion (sala_cinema_id, sala_numero, fecha, pelicula_id)  VALUES "
                + "(" + funcion.getSala().getCine().getId_cinema() + "," + funcion.getSala().getNumero()
                + " ,'" + funcion.getFecha() + "','"
                + funcion.getPelicula().getId_pelicula() + "' );";
  System.out.println(sql);
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving funcion ", e);
        }

        return funcion;
    }

    public void delete(Funcion funcion) throws SQLException {
       
        String insertTableSQL = "delete from bd_cine.funcion where fecha = ?"
                + funcion.getFecha() + "' ;";
       
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete funcion ", e);
        }
    }

}

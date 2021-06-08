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
import java.util.List;
import modelo.Sala;

/**
 *
 * @author Gaby
 */
public class SalaDaoImpl implements SalaDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;
    private CineDao cineDao;

    public SalaDaoImpl() throws SQLException {

        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
        this.cineDao = new CineDaoImpl();
    }

    @Override
    public Sala save(Sala sala) throws SQLException {
        String sql = "INSERT INTO bd_cine.sala (cinema_id, numero, capacidad) VALUES (?, ?, ?); ";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
    ps.setInt(1, sala.getCine().getId_cinema());
    ps.setInt(2, sala.getNumero());
    ps.setInt(3, sala.getCapacidad());
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  sala ", e);
        }

        return sala;
    }

    @Override
    public List<Sala> findAll() throws SQLException {

        List<Sala> salaList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.sala";

        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                salaList = new ArrayList<>();
                while (rs.next()) {
                    Sala sala = new Sala();
                    sala.setCine(this.cineDao.findById(rs.getInt("cinema_id")));
                    sala.setNumero((rs.getInt("numero")));
                    sala.setCapacidad((rs.getInt("capacidad")));
                    salaList.add(sala);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding sala ", e);
        }
        return salaList;
    }

    @Override
    public Sala update(Sala sala) throws SQLException {
        String sql = "update bd_cine.sala set cinema_id = ? , capacidad= ? where numero = ?" ;

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
             ps.setInt(1, sala.getCine().getId_cinema());
             ps.setInt(2, sala.getCapacidad());
             ps.setInt(3, sala.getNumero());
   
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update sala ", e);
        }
        return sala;
    }

    @Override
    public void delete(Sala sala) throws SQLException {
        String insertTableSQL = "delete from bd_cine.sala where numero = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setInt(1, sala.getNumero());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete sala ", e);
        }
    }

    @Override
    public Sala findById(int numero) throws SQLException {

        Sala sala = null;
        String selectTableSQL = "SELECT cinema_id, numero, capacidad  from bd_cine.sala where numero = ?;";
              

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                sala = new Sala();
                while (rs.next()) {
            
                    sala.setCine(this.cineDao.findById(rs.getInt("cinema_id")));
                    sala.setNumero((rs.getInt("numero")));
                    sala.setCapacidad((rs.getInt("capacidad")));
                    

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  sala ", e);
        }
        return sala;

    }

    public List<Sala>  findSalaByCine(int cinema_id)throws SQLException{
         List<Sala> salaList = null;
        String selectTableSQL = "SELECT cinema_id, numero, capacidad  from bd_cine.sala where cinema_id = ?;";

        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, cinema_id);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                salaList = new ArrayList<>();
                while (rs.next()) {
                    Sala sala = new Sala();
                    sala.setCine(this.cineDao.findById(rs.getInt("cinema_id")));
                    sala.setNumero((rs.getInt("numero")));
                    sala.setCapacidad((rs.getInt("capacidad")));
                    salaList.add(sala);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding sala ", e);
        }
        return salaList;
    }
    

    

}



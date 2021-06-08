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
import modelo.Cine;
import modelo.Usuario;

/**
 *
 * @author Gaby
 */
public class CineDaoImpl implements CineDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;

    public CineDaoImpl() throws SQLException {

        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();

    }

    @Override
    public Cine save(Cine cine) throws SQLException {
        String sql = "INSERT INTO bd_cine.cine (id_cinema, nombre, direccion ) VALUES (?, ?, ?); ";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, cine.getId_cinema());
            ps.setString(2, cine.getNombre());
            ps.setString(3, cine.getDireccion());
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  cine ", e);
        }

        return cine;
    }

    @Override
    public List<Cine> findAll() throws SQLException {

        List<Cine> cineList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.cine";

        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                cineList = new ArrayList<>();
                while (rs.next()) {
                    Cine cine = new Cine();
                    cine.setId_cinema(rs.getInt("id_cinema"));
                    cine.setNombre(rs.getString("nombre"));
                    cine.setDireccion(rs.getString("direccion"));
                    cineList.add(cine);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding cine ", e);
        }
        return cineList;
    }

    @Override
    public Cine update(Cine cine) throws SQLException {
        String sql = "update bd_cine.cine set nombre = ? , direccion = ? where id_cinema = ?";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
         ps.setString(1, cine.getNombre());
            ps.setString(2, cine.getDireccion());
             ps.setInt(3, cine.getId_cinema());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update cine ", e);
        }
        return cine;
    }

    @Override
    public void delete(Cine cine) throws SQLException {
        String insertTableSQL = "delete from bd_cine.cine where id_cinema = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setInt(1, cine.getId_cinema());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete cine ", e);
        }
    }

    @Override
    public Cine findById(int id_cinema) throws SQLException {

        Cine cine = null;
        String selectTableSQL = "SELECT id_cinema, nombre, direccion  from bd_cine.cine where id_cinema= ?;";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
             ps.setInt(1, id_cinema);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                cine = new Cine();
                while (rs.next()) {

                    cine.setId_cinema(rs.getInt("id_cinema"));
                    cine.setNombre(rs.getString("nombre"));
                    cine.setDireccion(rs.getString("direccion"));
               

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  cine ", e);
        }
        return cine;

    }

    

}

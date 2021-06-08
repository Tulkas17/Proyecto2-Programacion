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
import modelo.Pelicula;


/**
 *
 * @author Gaby
 */
public class PeliculaDaoImpl implements PeliculaDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;

    public PeliculaDaoImpl() throws SQLException {

        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
      
    }

    @Override
    public Pelicula save(Pelicula pelicula) throws SQLException {
        String sql = "INSERT INTO bd_cine.pelicula (id_pelicula, titulo, poster_path, movie_data ) VALUES (?, ?, ?, ?); ";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, pelicula.getId_pelicula());
            ps.setString(2, pelicula.getTitulo());
            ps.setString(3, pelicula.getPoster_path());
            ps.setString(4, pelicula.getMovie_data());
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  pelicula ", e);
        }

        return pelicula;
    }

    @Override
    public List<Pelicula> findAll() throws SQLException {

        List<Pelicula> peliculaList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.pelicula";

        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                peliculaList = new ArrayList<>();
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setId_pelicula(rs.getString("id_pelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setPoster_path(rs.getString("poster_path"));
                    pelicula.setMovie_data(rs.getString("movie_data"));
                  
                    peliculaList.add(pelicula);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding pelicula ", e);
        }
        return peliculaList;
    }

    @Override
    public Pelicula update(Pelicula pelicula) throws SQLException {
        String sql = "update bd_cine.pelicula set titulo = ? , poster_path = ? ,movie_data = ? where id_pelicula = ?" ;

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
           
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getPoster_path());
            ps.setString(3, pelicula.getMovie_data());
            ps.setString(4, pelicula.getId_pelicula());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update pelicula ", e);
        }
        return pelicula;
    }

    @Override
    public void delete(Pelicula pelicula) throws SQLException {
        String insertTableSQL = "delete from bd_cine.pelicula where id_pelicula = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setString(1, pelicula.getId_pelicula());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete pelicula ", e);
        }
    }

    @Override
    public Pelicula findById(String id_pelicula) throws SQLException {

        Pelicula pelicula = null;
        String selectTableSQL = "SELECT id_pelicula, titulo, poster_path, movie_data  from bd_cine.pelicula where id_pelicula = ?;";
              

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setString(1, id_pelicula);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                pelicula = new Pelicula();
                while (rs.next()) {
            
                 pelicula.setId_pelicula(rs.getString("id_pelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setPoster_path(rs.getString("poster_path"));
                    pelicula.setMovie_data(rs.getString("movie_data"));
                    

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  pelicula ", e);
        }
        return pelicula;

    }
    


}

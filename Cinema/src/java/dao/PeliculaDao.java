/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Pelicula;

/**
 *
 * @author Gaby
 */
public interface PeliculaDao {
      public Pelicula findById(String id_pelicula) throws SQLException;

    public Pelicula save(Pelicula pelicula) throws SQLException;

    public List<Pelicula> findAll() throws SQLException;

    public Pelicula update(Pelicula pelicula) throws SQLException;

    public void delete(Pelicula pelicula) throws SQLException;
}

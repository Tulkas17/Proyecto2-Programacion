/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.Funcion;

/**
 *
 * @author Gaby
 */
public interface FuncionDao {
    //falta poner fecha
      public Funcion findByDate(Date date) throws SQLException;

    public List<Funcion> findFuncionesByPelicula(String id_pelicula) throws SQLException;
    
     public List<Funcion> findFuncionesBySala(int id_sala) throws SQLException;

       public List<Funcion> findFuncionesByCine(int id_cine) throws SQLException;
    public List<Funcion> findAll() throws SQLException;
    
    public Funcion save(Funcion funcion) throws SQLException;

    public void delete(Funcion funcion) throws SQLException;
    
    
}

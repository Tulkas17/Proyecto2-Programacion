/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Sala;

/**
 *
 * @author Gaby
 */
public interface SalaDao {
    
     public Sala findById(int numero) throws SQLException;
     public List<Sala>  findSalaByCine(int cinema_id)throws SQLException;
    public Sala save(Sala sala) throws SQLException;

    public List<Sala> findAll() throws SQLException;

    public Sala update(Sala sala) throws SQLException;

    public void delete(Sala sala) throws SQLException;
    
 
}

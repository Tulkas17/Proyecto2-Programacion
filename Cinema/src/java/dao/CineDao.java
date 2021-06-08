/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Cine;


/**
 *
 * @author Gaby
 */
public interface CineDao {
     public Cine findById(int id_cinema) throws SQLException;

    public Cine save(Cine cine) throws SQLException;

    public List<Cine> findAll() throws SQLException;

    public Cine update(Cine cine) throws SQLException;

    public void delete(Cine cine) throws SQLException;
    
 
}

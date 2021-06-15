/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Tiquete;

/**
 *
 * @author Gaby
 */
public interface TiqueteDao {
    
    public List<Tiquete> findTiqueteByFactura(int factura_seq) throws SQLException;

     public Tiquete findTiqueteById(int id_tiquete) throws SQLException;
     
    public Tiquete save(Tiquete tiquete) throws SQLException;

    public List<Tiquete> findAll() throws SQLException;

    public Tiquete update(Tiquete tiquete) throws SQLException;

    public void delete(Tiquete  tiquete) throws SQLException;
}

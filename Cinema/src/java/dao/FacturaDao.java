/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Factura;

/**
 *
 * @author Gaby
 */
public interface FacturaDao {

    public Factura findById(int seq_factura) throws SQLException;

    public List<Factura> findFacturasByCliente(String id_cliente) throws SQLException;

    public Factura findByCliente(String id_cliente) throws SQLException;

    public Factura save(Factura factura) throws SQLException;

    public Factura update(Factura factura) throws SQLException;

    public void delete(Factura factura) throws SQLException;
    
    public List<Factura> findAll() throws SQLException;

}
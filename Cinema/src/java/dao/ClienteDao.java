/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Cliente;
import modelo.Usuario;

/**
 *
 * @author Gaby
 */
public interface ClienteDao{
        
    public Cliente findById(String id_cliente) throws SQLException;
    
    public Cliente findByIdUsuario(String id_usuario) throws SQLException;
    public Cliente save(Cliente cliente) throws SQLException;

    public List<Cliente> findAll() throws SQLException;

    public Cliente update(Cliente cliente) throws SQLException;

    public void delete(Cliente cliente) throws SQLException;
    
    //public int validar(Cliente cliente) throws SQLException;

}


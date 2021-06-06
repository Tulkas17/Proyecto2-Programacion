/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
//import logica.Rol;
import modelo.Usuario;

/**
 *
 * @author Gaby
 */
public interface UsuarioDao{
        
    public Usuario findById(String id_usuario) throws SQLException;

    public Usuario save(Usuario usuario) throws SQLException;

    public List<Usuario> findAll() throws SQLException;

    public Usuario update(Usuario usuario) throws SQLException;

    public void delete(Usuario usuario) throws SQLException;
    
    public int validar(Usuario usuario) throws SQLException;

}
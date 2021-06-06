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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author Gaby
 */
public class UsuarioDaoImpl implements UsuarioDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;

    public UsuarioDaoImpl() throws SQLException {

        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
      
    }

    @Override
    public Usuario save(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO bd_cine.usuario (id_usuario, clave, rol) VALUES (?, ?, ?); ";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, usuario.getId_usuario());
            ps.setString(2, usuario.getClave());
            ps.setInt(3, usuario.getRol());
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  usuario ", e);
        }

        return usuario;
    }

    @Override
    public List<Usuario> findAll() throws SQLException {

        List<Usuario> usuarioList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.usuario";

        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                usuarioList = new ArrayList<>();
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(rs.getString("id_usuario"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setRol(rs.getInt("rol"));
                    usuarioList.add(usuario);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding usuario ", e);
        }
        return usuarioList;
    }

    @Override
    public Usuario update(Usuario usuario) throws SQLException {
        String sql = "update bd_cine.usuario set clave = ? , rol = ? where id_usuario = ?" ;

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, usuario.getClave());
            ps.setInt(2, usuario.getRol());
            ps.setString(3,usuario.getId_usuario());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update usuario ", e);
        }
        return usuario;
    }

    @Override
    public void delete(Usuario usuario) throws SQLException {
        String insertTableSQL = "delete from bd_cine.usuario where id_usuario = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setString(1,usuario.getId_usuario());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete usuario ", e);
        }
    }

    @Override
    public Usuario findById(String id_usuario) throws SQLException {

        Usuario usuario = null;
        String selectTableSQL = "SELECT id_usuario, clave, rol  from bd_cine.usuario where id_usuario = ?;";
              

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setString(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                usuario = new Usuario();
                while (rs.next()) {
            
                    usuario.setId_usuario(rs.getString("id_usuario"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setRol(rs.getInt("rol"));
                    

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  usuario ", e);
        }
        return usuario;

    }
    

    @Override
    public int validar(Usuario usuario) {

        int r = 0;
        try {
            String sql = "Select * from bd_cine.usuario where id_usuario=? and clave=?";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, usuario.getId_usuario());
            ps.setString(2, usuario.getClave());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = r + 1;
                usuario.setId_usuario(rs.getString("id_usuario"));
                usuario.setClave(rs.getString("clave"));
            }
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }

    }

}


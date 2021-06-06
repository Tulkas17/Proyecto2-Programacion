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
import modelo.Cliente;
import modelo.Usuario;

/**
 *
 * @author Gaby
 */
public class ClienteDaoImpl implements ClienteDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;
    private UsuarioDao usuarioDao;

    public ClienteDaoImpl() throws SQLException {

        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
        usuarioDao = new UsuarioDaoImpl();
    }

    @Override
    public Cliente findById(String id_cliente) throws SQLException {
        Cliente cliente = null;
        String selectTableSQL = "SELECT c.id_cliente, c.apellidos , c.nombre, c.telefono , c.tarjeta_pago, c.usuario_id "
                + "FROM bd_cine.cliente c inner join bd_cine.usuario u on "
                + "c.usuario_id = u.id_usuario where c.id_cliente  = ?";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setString(1, id_cliente);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                cliente = new Cliente();
                while (rs.next()) {
                    cliente.setId_cliente(rs.getString("id_cliente"));
                    cliente.setApellidos(rs.getString("apellidos"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setTarjeta_pago(rs.getString("tarjeta_pago"));
                    cliente.setUsuario(this.usuarioDao.findById(rs.getString("usuario_id")));

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  cliente ", e);
        }

        return cliente;
    }

    @Override
    public Cliente findByIdUsuario(String id_usuario) throws SQLException {
        Cliente cliente = null;
        String selectTableSQL = "SELECT c.id_cliente, c.apellidos , c.nombre, c.telefono , c.tarjeta_pago, c.usuario_id "
                + "FROM bd_cine.cliente c inner join bd_cine.usuario u on "
                + "c.usuario_id = u.id_usuario where c.usuario_id  = ?";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setString(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                cliente = new Cliente();
                while (rs.next()) {
                    cliente.setId_cliente(rs.getString("id_cliente"));
                    cliente.setApellidos(rs.getString("apellidos"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setTarjeta_pago(rs.getString("tarjeta_pago"));
                    cliente.setUsuario(this.usuarioDao.findById(rs.getString("usuario_id")));
                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  cliente ", e);
        }

        return cliente;
    }

    @Override
    public Cliente save(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO bd_cine.cliente ( id_cliente, apellidos , nombre, telefono , tarjeta_pago, usuario_id) VALUES (?, ?, ?, ? , ?, ?); ";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, cliente.getId_cliente());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getTarjeta_pago());
            ps.setString(6, cliente.getUsuario().getId_usuario());

            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  cliente ", e);
        }

        return cliente;
    }

    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clienteList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.cliente";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                clienteList = new ArrayList<>();
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId_cliente(rs.getString("id_cliente"));
                    cliente.setApellidos(rs.getString("apellidos"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setTarjeta_pago(rs.getString("tarjeta_pago"));
                    cliente.setUsuario(this.usuarioDao.findById(rs.getString("usuario_id")));
                    clienteList.add(cliente);
                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding cliente ", e);
        }
        return clienteList;
    }

    @Override
    public Cliente update(Cliente cliente) throws SQLException {
        String sql = "update bd_cine.cliente set apellidos = ? , nombre = ? ,"
                + "telefono = ? , tarjeta_pago = ?, usuario_id = ? where id_cliente = ?";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, cliente.getApellidos());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getTarjeta_pago());
            ps.setString(5, cliente.getUsuario().getId_usuario());
            ps.setString(6, cliente.getId_cliente());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update cliente ", e);
        }
        return cliente;
    }

    @Override
    public void delete(Cliente cliente) throws SQLException {
        String insertTableSQL = "delete from bd_cine.cliente where id_cliente = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setString(1, cliente.getId_cliente());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete cliente ", e);
        }
    }
}

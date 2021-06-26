/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Factura;

/**
 *
 * @author Gaby
 */
public class FacturaDaoImpl implements FacturaDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;
    private ClienteDao clienteDao;

    public FacturaDaoImpl() throws SQLException {
        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
        this.clienteDao = new ClienteDaoImpl();
    }

    @Override
    public Factura findById(int seq_factura) throws SQLException {
        Factura factura = null;
        String selectTableSQL = "SELECT f.seq_factura, f.fecha, f.cliente_id, f.tarjeta_pago "
                + "FROM bd_cine.factura f inner join bd_cine.cliente c on "
                + "f.cliente_id = c.id_cliente where f.seq_factura = ?";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, seq_factura);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                factura = new Factura();
                while (rs.next()) {
                    Cliente cliente = this.clienteDao.findById(rs.getString("cliente_id"));
                    factura.setSeq_factura(rs.getInt("seq_factura"));
                    factura.setCliente(cliente);
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    factura.setFecha(rs.getString("fecha"));
                    factura.setTarjeta_pago(rs.getString("tarjeta_pago"));

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  factura ", e);
        }

        return factura;
    }

    @Override
    public List<Factura> findFacturasByCliente(String id_cliente) throws SQLException {
        List<Factura> facturaList = null;
        String selectTableSQL = "SELECT f.seq_factura, f.fecha, f.cliente_id, f.tarjeta_pago "
                + "FROM bd_cine.factura f inner join bd_cine.cliente c on "
                + "f.cliente_id = c.id_cliente where f.cliente_id = ?";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setString(1, id_cliente);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                facturaList = new ArrayList<>();
                while (rs.next()) {
                    Factura factura = new Factura();
                    Cliente cliente = this.clienteDao.findById(rs.getString("cliente_id"));
                    factura.setSeq_factura(rs.getInt("seq_factura"));
                    factura.setCliente(cliente);
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    factura.setFecha(rs.getString("fecha"));
                     factura.setTarjeta_pago(rs.getString("tarjeta_pago"));
                    facturaList.add(factura);
                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding factura ", e);
        }
        return facturaList;
    }

    @Override
    public Factura findByCliente(String id_cliente) throws SQLException {
        Factura factura = null;
        String selectTableSQL = "SELECT f.seq_factura, f.fecha, f.cliente_id, f.tarjeta_pago "
                + "FROM bd_cine.factura f inner join bd_cine.cliente c on "
                + "f.cliente_id = c.id_cliente where f.cliente_id = ?";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setString(1, id_cliente);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                factura = new Factura();
                while (rs.next()) {
                    Cliente cliente = this.clienteDao.findById(rs.getString("cliente_id"));
                    factura.setSeq_factura(rs.getInt("seq_factura"));
                    factura.setCliente(cliente);
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    factura.setFecha(rs.getString("fecha"));
                    factura.setTarjeta_pago(rs.getString("tarjeta_pago"));

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  factura ", e);
        }

        return factura;
    }

    @Override
    public Factura save(Factura factura) throws SQLException {
      
String sql = "INSERT INTO bd_cine.factura (seq_factura, fecha, cliente_id, tarjeta_pago)  VALUES "
                + "(" + factura.getSeq_factura() + ",'" + factura.getFecha() + "' ,'" +factura.getCliente().getId_cliente() + "','"
                + factura.getCliente().getTarjeta_pago() + "' );";
System.out.println(sql);
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  factura ", e);
        }

        return factura;
    }

    @Override
    public Factura update(Factura factura) throws SQLException {
        String sql = "update bd_cine.factura set fecha = '" +factura.getFecha()+"' where seq_factura ="+factura.getSeq_factura()+";";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update factura ", e);
        }
        return factura;
    }

    @Override
    public void delete(Factura factura) throws SQLException {
        String insertTableSQL = "delete from bd_cine.factura where seq_factura = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
            ps.setInt(1, factura.getSeq_factura());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete factura ", e);
        }
    }

    @Override
    public List<Factura> findAll() throws SQLException {
        List<Factura> facturaList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.factura";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                facturaList = new ArrayList<>();
                while (rs.next()) {
                    Factura factura = new Factura();
                    Cliente cliente = this.clienteDao.findById(rs.getString("cliente_id"));
                    factura.setSeq_factura(rs.getInt("seq_factura"));
                    factura.setCliente(cliente);
                    java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(rs.getString("fecha"));
                    factura.setFecha(rs.getString("fecha"));
                     factura.setTarjeta_pago(rs.getString("tarjeta_pago"));
                    facturaList.add(factura);
                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding factura ", e);
        }
        return facturaList;
    }
}

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
import modelo.Asiento_sala;
import modelo.Sala;

/**
 *
 * @author Gaby
 */
public class AsientoSalaDaoImpl implements AsientoSalaDao {

    private Connection dbConnection;
    private Statement statement = null;
    private ResultSet rs;
    private SalaDao salaDao;

    public AsientoSalaDaoImpl() throws SQLException {

        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
        this.salaDao = new SalaDaoImpl();
    }

    @Override
    public Asiento_sala findByFilaPosicion(char fila, int posicion) throws SQLException {
        Asiento_sala asientoSala = null;

        String selectTableSQL = "SELECT a.sala_cinema_id, a.sala_numero, a.fila, a.posicion, a.disponible"
                + "  from bd_cine.asiento_sala a inner join bd_cine.sala s "
                + "on a.sala_numero = s.numero where fila = ? and posicion = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);

            ps.setString(1, Character.toString(fila));
            ps.setInt(2, posicion);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                asientoSala = new Asiento_sala();
                while (rs.next()) {


                    asientoSala.setSala_cinema_id(rs.getInt("sala_cinema_id"));
                    asientoSala.setSala_numero(rs.getInt("sala_numero"));
                    asientoSala.setFila(rs.getString("fila").charAt(0));
                    asientoSala.setPosicion(rs.getInt("posicion"));
                    asientoSala.setDisponible(rs.getBoolean("disponible"));

                }
            }
        } catch (Exception e) {
            System.err.printf("Failes to find  asiento sala ", e);
        }
        return asientoSala;
    }

    @Override
    public List<Asiento_sala> findAsientoBySala(int sala_numero) throws SQLException {
        List<Asiento_sala> asientoSalaList = null;
               String selectTableSQL = "SELECT a.sala_cinema_id, a.sala_numero, a.fila, a.posicion, a.disponible"
                + "  from bd_cine.asiento_sala a inner join bd_cine.sala s "
                + "on a.sala_numero = s.numero where a.sala_numero = ? ;";

        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ps.setInt(1, sala_numero);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                asientoSalaList = new ArrayList<>();
                while (rs.next()) {
                    Asiento_sala asientoSala = new Asiento_sala();
                    asientoSala.setSala_cinema_id(rs.getInt("sala_cinema_id"));
                    asientoSala.setSala_numero(rs.getInt("sala_numero"));
                    asientoSala.setFila(rs.getString("fila").charAt(0));
                    asientoSala.setPosicion(rs.getInt("posicion"));
                    asientoSala.setDisponible(rs.getBoolean("disponible"));
                    asientoSalaList.add(asientoSala);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding asiento sala by sala ", e);
        }
        return asientoSalaList;
    }
 @Override
    public Asiento_sala save(Asiento_sala asientoSala) throws SQLException {
        String sql = "INSERT INTO bd_cine.asiento_sala (sala_cinema_id, sala_numero, fila, posicion, disponible)"
                + "VALUES (?, ?, ?, ?, ?); ";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, asientoSala.getSala().getCine().getId_cinema());
            ps.setInt(2, asientoSala.getSala().getNumero());

            ps.setString(3, Character.toString(asientoSala.getFila()));
            ps.setInt(4, asientoSala.getPosicion());
            ps.setBoolean(5, asientoSala.getDisponible());
            ps.execute();
            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error saving  asiento sala ", e);
        }

        return asientoSala;
    }

    @Override
    public List<Asiento_sala> findAll() throws SQLException {
        List<Asiento_sala> asientoSalaList = null;
        String selectTableSQL = "SELECT * FROM bd_cine.asiento_sala";

        try {
            // execute select SQL stetement
            PreparedStatement ps = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                asientoSalaList = new ArrayList<>();
                while (rs.next()) {
                    Asiento_sala asientoSala = new Asiento_sala();
                    Sala sala = this.salaDao.findById(rs.getInt("sala_numero"));
                    asientoSala.setSala_cinema_id(rs.getInt("sala_cinema_id"));
                    asientoSala.setSala_numero(rs.getInt("sala_numero"));
                    asientoSala.setFila(rs.getString("fila").charAt(0));
                    asientoSala.setPosicion(rs.getInt("posicion"));
                    asientoSala.setDisponible(rs.getBoolean("disponible"));
                    asientoSalaList.add(asientoSala);

                }
            }

        } catch (Exception e) {
            System.err.printf("Error finding asiento sala ", e);
        }
        return asientoSalaList;
    }

    public Asiento_sala update(Asiento_sala asientoSala) throws SQLException {
    String sql = "update bd_cine.asiento_sala set  disponible= ? "
            + "where fila = ? and posicion = ?" ;

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setBoolean(1, asientoSala.getDisponible());
            ps.setString(2, Character.toString(asientoSala.getFila()));
            ps.setInt(3, asientoSala.getPosicion());
         ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error update asientoSala ", e);
        }
        return asientoSala;
    }
 @Override
    public void delete(Asiento_sala asientoSala) throws SQLException {
   String insertTableSQL = "delete from bd_cine.asiento_sala where fila = ? and posicion = ? ;";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(insertTableSQL);
             ps.setString(1, Character.toString(asientoSala.getFila()));
            ps.setInt(2, asientoSala.getPosicion());
            ps.executeUpdate();

            JdbcUtil.closeConnection();
        } catch (Exception e) {
            System.err.printf("Error delete asiento sala ", e);
        }
    }
}

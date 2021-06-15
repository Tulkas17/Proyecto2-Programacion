/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Asiento_sala;

/**
 *
 * @author Gaby
 */
public interface AsientoSalaDao {

    public Asiento_sala findByFilaPosicion(char fila, int posicion) throws SQLException;

    public List<Asiento_sala> findAsientoBySala(int sala_numero) throws SQLException;

    public Asiento_sala save(Asiento_sala asientoSala) throws SQLException;

    public List<Asiento_sala> findAll() throws SQLException;

    public Asiento_sala update(Asiento_sala asientoSala) throws SQLException;

    public void delete(Asiento_sala asientoSala) throws SQLException;
}

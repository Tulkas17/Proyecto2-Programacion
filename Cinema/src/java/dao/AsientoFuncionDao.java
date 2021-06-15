/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.Asiento_funcion;


/**
 *
 * @author Gaby
 */
public interface AsientoFuncionDao {
    public Asiento_funcion findByFilaPosicion(char fila, int posicion) throws SQLException;

    public List<Asiento_funcion> findAsientoByFuncionDate(Date fechaFuncion) throws SQLException;

    public Asiento_funcion save(Asiento_funcion asientoFuncion) throws SQLException;

    public List<Asiento_funcion> findAll() throws SQLException;

    public Asiento_funcion update(Asiento_funcion asientoFuncion) throws SQLException;

    public void delete(Asiento_funcion asientoFuncion) throws SQLException;
}

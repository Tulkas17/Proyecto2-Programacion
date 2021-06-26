/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author oscar
 */
public class Tiquete implements Serializable {

    int id_tiquete;
    int factura_seq;
    int asiento_funcion_sala_cinema;
    int asiento_funcion_sala_numero;
    String asiento_funcion_fecha;
    char asiento_funcion_fila;
    int asiento_funcion_posicion;
    double monto;
    Asiento_funcion asientoFuncion;
    Factura factura;

    public Tiquete(int id_tiquete, Asiento_funcion asientoFuncion, Factura factura, double monto) {
        this.id_tiquete = id_tiquete;
        this.factura_seq = factura_seq;
        this.factura =factura;
        this.asientoFuncion =asientoFuncion;
        this.asiento_funcion_sala_cinema = asientoFuncion.getFuncion_sala_cinema_id();
        this.asiento_funcion_sala_numero = asientoFuncion.getFuncion_sala_numero();
        this.asiento_funcion_fecha = asientoFuncion.getFuncion_fecha();
        this.asiento_funcion_fila = asientoFuncion.getFila();
        this.asiento_funcion_posicion = asientoFuncion.getPosicion();
        this.monto = monto;
    } 

    public Tiquete() {

    }

    public int getFactura_seq() {
        return factura_seq;
    }

    public void setFactura_seq(int factura_seq) {
        this.factura_seq = factura_seq;
    }

    public int getAsiento_funcion_sala_cinema() {
        return asiento_funcion_sala_cinema;
    }

    public void setAsiento_funcion_sala_cinema(int asiento_funcion_sala_cinema) {
        this.asiento_funcion_sala_cinema = asiento_funcion_sala_cinema;
    }

    public int getAsiento_funcion_sala_numero() {
        return asiento_funcion_sala_numero;
    }

    public void setAsiento_funcion_sala_numero(int asiento_funcion_sala_numero) {
        this.asiento_funcion_sala_numero = asiento_funcion_sala_numero;
    }

    public String getAsiento_funcion_fecha() {
        return asiento_funcion_fecha;
    }

    public void setAsiento_funcion_fecha(String asiento_funcion_fecha) {
        this.asiento_funcion_fecha = asiento_funcion_fecha;
    }

    public char getAsiento_funcion_fila() {
        return asiento_funcion_fila;
    }

    public void setAsiento_funcion_fila(char asiento_funcion_fila) {
        this.asiento_funcion_fila = asiento_funcion_fila;
    }

    public int getAsiento_funcion_posicion() {
        return asiento_funcion_posicion;
    }

    public void setAsiento_funcion_posicion(int asiento_funcion_posicion) {
        this.asiento_funcion_posicion = asiento_funcion_posicion;
    }

  


    public Asiento_funcion getAsientoFuncion() {
        return asientoFuncion;
    }

    public void setAsientoFuncion(Asiento_funcion asientoFuncion) {
        this.asientoFuncion = asientoFuncion;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    

    public int getId_tiquete() {
        return id_tiquete;
    }

    public void setId_tiquete(int id_tiquete) {
        this.id_tiquete = id_tiquete;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Tiquete{" + "id_tiquete=" + id_tiquete + ", factura_seq=" 
                + factura_seq + ", asiento_funcion_sala_cinema=" + asiento_funcion_sala_cinema 
                + ", asiento_funcion_sala_numero=" + asiento_funcion_sala_numero + ", asiento_funcion_fecha="
                + asiento_funcion_fecha + ", asiento_funcion_fila=" + asiento_funcion_fila 
                + ", siento_funcion_posion=" + asiento_funcion_posicion + ", monto=" + monto + '}';
    }

    

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_tiquete", this.getId_tiquete());
        j.put("factura_seq", this.getFactura_seq());
        j.put("asiento_funcion_sala_cinema", this.getAsiento_funcion_sala_cinema());
        j.put("asiento_funcion_sala_numero", this.getAsiento_funcion_sala_numero());
        j.put("asiento_funcion_fecha", this.getAsiento_funcion_fecha());
        j.put("asiento_funcion_fila", this.getAsiento_funcion_fila());
        j.put("asiento_funcion_posicion", this.getAsiento_funcion_posicion());
        j.put("monto", this.getMonto());
        return j;
    }
}

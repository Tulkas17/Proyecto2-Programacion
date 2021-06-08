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
    Date asiento_funcion_fecha;
    char asiento_funcion_fila;
    int siento_funcion_posion;
    double monto;

    public Tiquete(int id_tiquete, int factura_seq, int asiento_funcion_sala_cinema, int asiento_funcion_sala_numero, Date asiento_funcion_fecha, char asiento_funcion_fila, int siento_funcion_posion, double monto) {
        this.id_tiquete = id_tiquete;
        this.factura_seq = factura_seq;
        this.asiento_funcion_sala_cinema = asiento_funcion_sala_cinema;
        this.asiento_funcion_sala_numero = asiento_funcion_sala_numero;
        this.asiento_funcion_fecha = asiento_funcion_fecha;
        this.asiento_funcion_fila = asiento_funcion_fila;
        this.siento_funcion_posion = siento_funcion_posion;
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

    public Date getAsiento_funcion_fecha() {
        return asiento_funcion_fecha;
    }

    public void setAsiento_funcion_fecha(Date asiento_funcion_fecha) {
        this.asiento_funcion_fecha = asiento_funcion_fecha;
    }

    public char getAsiento_funcion_fila() {
        return asiento_funcion_fila;
    }

    public void setAsiento_funcion_fila(char asiento_funcion_fila) {
        this.asiento_funcion_fila = asiento_funcion_fila;
    }

    public int getSiento_funcion_posion() {
        return siento_funcion_posion;
    }

    public void setSiento_funcion_posion(int siento_funcion_posion) {
        this.siento_funcion_posion = siento_funcion_posion;
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
        return "Tiquete{" + "id_tiquete=" + id_tiquete + ", factura_seq=" + factura_seq + ", asiento_funcion_sala_cinema=" + asiento_funcion_sala_cinema + ", asiento_funcion_sala_numero=" + asiento_funcion_sala_numero + ", asiento_funcion_fecha=" + asiento_funcion_fecha + ", asiento_funcion_fila=" + asiento_funcion_fila + ", siento_funcion_posion=" + siento_funcion_posion + ", monto=" + monto + '}';
    }

    

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_tiquete", this.getId_tiquete());
        j.put("factura_seq", this.getFactura_seq());
        j.put("asiento_funcion_sala_cinema", this.getAsiento_funcion_sala_cinema());
        j.put("asiento_funcion_sala_numero", this.getAsiento_funcion_sala_numero());
        j.put("asiento_funcion_fecha", this.getAsiento_funcion_fecha());
        j.put("asiento_funcion_fila", this.getAsiento_funcion_fila());
        j.put("asiento_funcion_posicion", this.getSiento_funcion_posion());
        j.put("monto", this.getMonto());
        return j;
    }
}

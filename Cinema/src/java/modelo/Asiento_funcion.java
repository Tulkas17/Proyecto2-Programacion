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
public class Asiento_funcion implements Serializable {

    int funcion_sala_cinema_id;
    int funcion_sala_numero;
    Date funcion_fecha;
    char fila;
    int posicion;
    boolean ocupado;
    Funcion funcion;

    public Asiento_funcion(Funcion funcion, char fila, int posicion, boolean ocupado) {
        this.funcion_sala_cinema_id = funcion.getSala_cinema_id();
        this.funcion_sala_numero = funcion.getSala_numero();
        this.funcion_fecha = funcion.getFecha();
        this.fila = fila;
        this.posicion = posicion;
        this.ocupado = ocupado;
        this.funcion =funcion;
    }

    public Asiento_funcion() {
    }

    public int getFuncion_sala_cinema_id() {
        return funcion_sala_cinema_id;
    }

    public void setFuncion_sala_cinema_id(int funcion_sala_cinema_id) {
        this.funcion_sala_cinema_id = funcion_sala_cinema_id;
    }

    public int getFuncion_sala_numero() {
        return funcion_sala_numero;
    }

    public void setFuncion_sala_numero(int funcion_sala_numero) {
        this.funcion_sala_numero = funcion_sala_numero;
    }

    public Date getFuncion_fecha() {
        return funcion_fecha;
    }

    public void setFuncion_fecha(Date funcion_fecha) {
        this.funcion_fecha = funcion_fecha;
    }

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    
    @Override
    public String toString() {
        return "Asiento_funcion{" + "funcion_sala_cinema_id=" + funcion_sala_cinema_id + ", funcion_sala_numero=" + funcion_sala_numero + ", funcion_fecha=" + funcion_fecha + ", fila=" + fila + ", posicion=" + posicion + ", ocupado=" + ocupado + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("funcion_sala_cinema_id", this.getFuncion_sala_cinema_id());
        j.put("funcion_sala_numero", this.getFuncion_sala_numero());
        j.put("funcion_fecha", this.getFuncion_fecha());
        j.put("fila", this.getFila());
        j.put("posicion", this.getPosicion());
        j.put("ocupado", this.getOcupado());
        return j;
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import org.json.JSONObject;

/**
 *
 * @author oscar
 */
public class Asiento_funcion implements Serializable {

    Funcion funcion;
    int fila;
    int columna;
    int ocupado;

    public Asiento_funcion(Funcion funcion, int fila, int columna, int ocupado) {
        this.funcion = funcion;
        this.fila = fila;
        this.columna = columna;
        this.ocupado = ocupado;
    }

    public Asiento_funcion(int fila, int columna, int ocupado) {
        this.fila = fila;
        this.columna = columna;
        this.ocupado = ocupado;
    }

    public Asiento_funcion() {

    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getOcupado() {
        return ocupado;
    }

    public void setOcupado(int ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        return "Asiento_funcion{" + "funcion sin " + ", fila=" + fila + ", columna=" + columna
                + ", ocupado=" + ocupado + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("fila", this.getFila());
        j.put("columna", this.getColumna());
        j.put("ocupado", this.getOcupado());
        return j;
    }

}

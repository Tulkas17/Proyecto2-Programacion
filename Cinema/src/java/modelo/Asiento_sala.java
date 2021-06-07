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
public class Asiento_sala implements Serializable {

    int fila;
    int columan;

    public Asiento_sala(int fila, int columan) {
        this.fila = fila;
        this.columan = columan;
    }

    public Asiento_sala() {
        
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColuman() {
        return columan;
    }

    public void setColuman(int columan) {
        this.columan = columan;
    }

    @Override
    public String toString() {
        return "Asiento_sala{" + "fila=" + fila + ", columan=" + columan + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("fila", this.getFila());
        j.put("columan", this.getColuman());
        return j;
    }

}

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

    int sala_cinema_id;
    int sala_numero;
    char fila;
    int posicion;
    int disponible;

    public Asiento_sala(int sala_cinema, int sala_numero, char fila, int posicion, int disponible) {
        this.sala_cinema_id = sala_cinema;
        this.sala_numero = sala_numero;
        this.fila = fila;
        this.posicion = posicion;
        this.disponible = disponible;
    }

    public Asiento_sala() {
    }

    public int getSala_cinema_id() {
        return sala_cinema_id;
    }

    public void setSala_cinema_id(int sala_cinema_id) {
        this.sala_cinema_id = sala_cinema_id;
    }

    public int getSala_numero() {
        return sala_numero;
    }

    public void setSala_numero(int sala_numero) {
        this.sala_numero = sala_numero;
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

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Asiento_sala{" + "sala_cinema=" + sala_cinema_id + ", sala_numero=" + sala_numero + ", fila=" + fila + ", posicion=" + posicion + ", disponible=" + disponible + '}';
    }

    

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("sala_cinema_id", this.getSala_cinema_id());
        j.put("sala_numero", this.getSala_numero());
        j.put("fila", this.getFila());
        j.put("posicion", this.getPosicion());
        j.put("disponible", this.getDisponible());
        return j;
    }

}

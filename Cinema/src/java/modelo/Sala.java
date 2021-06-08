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
 * @author Gaby
 */
public class Sala implements Serializable {

    Cine cine;
    int numero;
    int capacidad;

    public Sala(Cine cinema, int numero, int capacidad) {
        this.cine = cinema;
        this.numero = numero;
        this.capacidad = capacidad;
    }

    public Sala() {
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Sala{" + "cinema_id=" + cine + ", numero=" + numero + ", capacidad=" + capacidad + '}';
    }

    

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("cinema_id", this.getCine().getId_cinema());
        j.put("numero", this.getNumero());
        j.put("capacidad", this.getCapacidad());
        return j;
    }

}

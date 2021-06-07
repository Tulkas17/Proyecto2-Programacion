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
public class Sala implements Serializable {

    int numero;
    int capacidad;

    public Sala(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
    }

    public Sala() {

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
        return "Sala{" + "numero=" + numero + ", capacidad=" + capacidad + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("fila", this.getNumero());
        j.put("columan", this.getCapacidad());
        return j;
    }
}

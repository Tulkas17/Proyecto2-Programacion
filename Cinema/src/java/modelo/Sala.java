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

    private Cine cine;
    private int numero;
    private int capacidad;

    public Sala(Cine cine, int numero, int capacidad) {
        this.cine = cine;
        this.numero = numero;
        this.capacidad = capacidad;
    }

  
public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_cinema", this.getCine().getId_cinema());
        j.put("numero", this.getNumero());
        j.put("capacidad", this.getCapacidad());
        return j;
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
        return "Sala{" + "cine=" + cine + ", numero=" + numero + ", capacidad=" + capacidad + '}';
    }

   
    
    

}

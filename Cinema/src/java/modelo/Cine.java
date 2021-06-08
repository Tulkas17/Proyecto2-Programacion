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
public class Cine implements Serializable{
    private int id_cinema;
    private String nombre;
    private String  direccion;

    public Cine(int id_cinema, String nombre, String direccion) {
        this.id_cinema = id_cinema;
        this.nombre = nombre;
        this.direccion = direccion;
    }

     public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_cinema", this.getId_cinema());
        j.put("direccion", this.getNombre());
        j.put("direccion", this.getDireccion());
        return j;
    }
    public Cine() {
    }

    public int getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(int id_cinema) {
        this.id_cinema = id_cinema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cine{" + "id_cinema=" + id_cinema + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
    
}

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
public class Cinema implements Serializable {

    int id_cinema;
    String direccion;

    public Cinema(int id_cinema, String direccion) {
        this.id_cinema = id_cinema;
        this.direccion = direccion;
    }
    
    public Cinema() {
        
    }

    public int getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(int id_cinema) {
        this.id_cinema = id_cinema;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cinema{" + "id_cinema=" + id_cinema + ", direccion=" + direccion + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_cinema", this.getId_cinema());
        j.put("direccion", this.getDireccion());
        return j;
    }

}

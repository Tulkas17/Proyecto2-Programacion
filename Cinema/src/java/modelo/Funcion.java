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
public class Funcion implements Serializable {

    int sala_cinema_id;
    int sala_numero;
    Date fecha;
    String pelicula_id;

    public Funcion(int sala_cinema_id, int sala_numero, Date fecha, String pelicula_id) {
        this.sala_cinema_id = sala_cinema_id;
        this.sala_numero = sala_numero;
        this.fecha = fecha;
        this.pelicula_id = pelicula_id;
    }

    public Funcion() {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(String pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    @Override
    public String toString() {
        return "Funcion{" + "sala_cinema_id=" + sala_cinema_id + ", sala_numero=" + sala_numero + ", fecha=" + fecha + ", pelicula_id=" + pelicula_id + '}';
    }


    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("sala_cinema_id", this.getSala_cinema_id());
        j.put("sala_numero", this.getSala_numero());
        j.put("fecha", this.getFecha());
        j.put("pelicula_id", this.getPelicula_id());
        return j;
    }

}

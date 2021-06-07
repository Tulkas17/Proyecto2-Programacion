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

    Sala sala;
    Pelicula pelicula_id_pelicula;
    Date fecha;

    public Funcion(Sala sala, Pelicula pelicula_id_pelicula, Date fecha) {
        this.sala = sala;
        this.pelicula_id_pelicula = pelicula_id_pelicula;
        this.fecha = fecha;
    }

    public Funcion(Pelicula pelicula_id_pelicula, Date fecha) {
        this.pelicula_id_pelicula = pelicula_id_pelicula;
        this.fecha = fecha;
    }

    public Funcion() {

    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula_id_pelicula() {
        return pelicula_id_pelicula;
    }

    public void setPelicula_id_pelicula(Pelicula pelicula_id_pelicula) {
        this.pelicula_id_pelicula = pelicula_id_pelicula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Funcion{" + "sin sala" + ", pelicula_id_pelicula="
                + pelicula_id_pelicula + ", fecha=" + fecha + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("sala", this.getSala());
        j.put("pelicula_id_pelicula", this.getPelicula_id_pelicula());
        j.put("fecha", this.getFecha());
        return j;
    }

}

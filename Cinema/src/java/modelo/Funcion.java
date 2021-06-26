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
    String fecha;
    String pelicula_id;
    Sala sala;
    Pelicula pelicula;

    public Funcion(String fecha, Sala sala, Pelicula pelicula) {
        this.fecha = fecha;
        this.sala = sala;
        this.pelicula = pelicula;
        this.pelicula_id = pelicula.getId_pelicula();
        this.sala_cinema_id = sala.getCine().getId_cinema();
        this.sala_numero = sala.getNumero();
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(String pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
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

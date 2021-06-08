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
public class Pelicula implements Serializable{
 private String id_pelicula;
 private String titulo;
 private String poster_path;
 private String movie_data ;

    public Pelicula(String id_pelicula, String titulo, String poster_path, String movie_data) {
        this.id_pelicula = id_pelicula;
        this.titulo = titulo;
        this.poster_path = poster_path;
        this.movie_data = movie_data;
    }
    
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_pelicula", this.getId_pelicula());
        j.put("titulo", this.getTitulo());
        j.put("poster_path", this.getPoster_path());
        j.put("movie_data", this.getMovie_data());
        return j;
    }

    public Pelicula() {
    }
    
    public String getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(String id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getMovie_data() {
        return movie_data;
    }

    public void setMovie_data(String movie_data) {
        this.movie_data = movie_data;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id_pelicula=" + id_pelicula + ", titulo=" + titulo + ", poster_path=" + poster_path + ", movie_data=" + movie_data + '}';
    }
 
 

}

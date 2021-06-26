/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.PeliculaDao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONObject;

@XmlRootElement(name = "lista-peliculas")
public class ListaPeliculas implements Serializable {

    public ListaPeliculas(){
        peliculas = new ArrayList<>();
    }

    public ListaPeliculas(List<Pelicula> peliculas) {
        this.peliculas = new ArrayList<>(peliculas);
    }

    public void agregar(PeliculaDao c) {
        peliculas.forEach((p) -> {
            try {
                c.save(p);
            } catch (SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
            }
        });
    }

    @Override
    public String toString() {
        return toJSON().toString(4);
    }

    public JSONObject toJSON() {
        JSONArray a = new JSONArray();
        peliculas.forEach((p) -> {
            a.put(p.toJSON());
        });

        JSONObject r = new JSONObject();
        r.put("lista-peliculas", a);
        return r;
    }

    @XmlElement(name = "pelicula")
    private final List<Pelicula> peliculas;
}

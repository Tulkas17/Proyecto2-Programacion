/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.CineDao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONObject;

@XmlRootElement(name = "lista-cines")
public class ListaCines implements Serializable {

    public ListaCines(){
        cines = new ArrayList<>();
    }

    public ListaCines(List<Cine> cines) {
        this.cines = new ArrayList<>(cines);
    }

    public void agregar(CineDao c) {
        cines.forEach((p) -> {
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
        cines.forEach((p) -> {
            a.put(p.toJSON());
        });

        JSONObject r = new JSONObject();
        r.put("lista-cines", a);
        return r;
    }

    @XmlElement(name = "cine")
    private final List<Cine> cines;
}

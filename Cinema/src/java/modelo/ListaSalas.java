/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.SalaDao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONObject;

@XmlRootElement(name = "lista-salas")
public class ListaSalas implements Serializable {

    public ListaSalas(){
      salas = new ArrayList<>();
    }

    public ListaSalas(List<Sala> salas) {
        this.salas = new ArrayList<>(salas);
    }

    public void agregar(SalaDao c) {
        salas.forEach((p) -> {
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
        salas.forEach((p) -> {
            a.put(p.toJSON());
        });

        JSONObject r = new JSONObject();
        r.put("lista-salas", a);
        return r;
    }

    @XmlElement(name = "sala")
    private final List<Sala> salas;
}

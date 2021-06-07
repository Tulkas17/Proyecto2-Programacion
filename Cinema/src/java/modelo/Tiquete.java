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
public class Tiquete implements Serializable {

    int id_tiquete;
    double monto;

    public Tiquete(int id_tiquete, double monto) {
        this.id_tiquete = id_tiquete;
        this.monto = monto;
    }

    public Tiquete() {

    }

    public int getId_tiquete() {
        return id_tiquete;
    }

    public void setId_tiquete(int id_tiquete) {
        this.id_tiquete = id_tiquete;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Tiquete{" + "id_tiquete=" + id_tiquete + ", monto=" + monto + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_tiquete", this.getId_tiquete());
        j.put("monto", this.getMonto());
        return j;
    }
}

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
public class Administrador implements Serializable {

    String id_admin;
    Usuario usuario;

    public Administrador(String id_usuario, Usuario usuario) {
        this.id_admin = id_usuario;
        this.usuario = usuario;
    }

    public Administrador() {

    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id_admin=" + id_admin + ", usuario=" + usuario + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_admin", this.getId_admin());
        j.put("id_usuario", this.getUsuario().getId_usuario());
        j.put("clave", this.getUsuario().getClave());
        j.put("rol", this.getUsuario().getRol());

        return j;
    }
}

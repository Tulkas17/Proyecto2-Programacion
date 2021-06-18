/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.json.JSONObject;

/**
 *
 * @author Gaby
 */
public class Cliente {

    String id_cliente;
    String apellidos;
    String nombre;
    String telefono;
    String tarjeta_pago;
    Usuario usuario;

    public Cliente(String id_cliente, String apellidos, String nombre, String telefono, String tarjeta_pago, Usuario usuario) {
        this.id_cliente = id_cliente;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tarjeta_pago = tarjeta_pago;
        this.usuario = usuario;
    }

    public Cliente() {

    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarjeta_pago() {
        return tarjeta_pago;
    }

    public void setTarjeta_pago(String tarjeta_pago) {
        this.tarjeta_pago = tarjeta_pago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", apellidos=" + apellidos + ", nombre=" + nombre + ", telefono=" + telefono + ", tarjeta_pago=" + tarjeta_pago + ", usuario=" + usuario + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_usuario", this.getId_cliente());
        j.put("tarjetaCredito", this.getTarjeta_pago());
        j.put("nombre", this.getNombre());
        j.put("apellidos", this.getApellidos());
        j.put("telefono", this.getTelefono());

        return j;
    }       
}

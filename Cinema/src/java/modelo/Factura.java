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
public class Factura implements Serializable {

    int seq_factura;
    Date fecha;
    Cliente cliente;

    public Factura(int seq_factura, Date fecha, Cliente cliente) {
        this.seq_factura = seq_factura;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Factura() {

    }

    public int getSeq_factura() {
        return seq_factura;
    }

    public void setSeq_factura(int seq_factura) {
        this.seq_factura = seq_factura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura{" + "seq_factura=" + seq_factura + ", fecha=" + fecha + ", cliente=" + cliente + '}';
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("cliente", this.getCliente());
        j.put("fecha", this.getFecha());
        j.put("cliente", this.getCliente());
        return j;
    }

}

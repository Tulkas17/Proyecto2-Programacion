
package modelo;

//import org.json.JSONObject;

import java.io.Serializable;



public class Usuario implements Serializable{

    String id_usuario;
    String clave;
    int rol;


    public Usuario(String id_usuario, String clave, int rol) {
        this.id_usuario = id_usuario;
        this.clave = clave;
        this.rol = rol;
    }

    public Usuario() {
    }

    
       
    
//    @Override
//    public String toString() {
//        return toJSON().toString(7);
//    }
//
//   public JSONObject toJSON() {
////        JSONObject r = new JSONObject();
////        r.put("id_usuario", getNombre_usuario());
////        r.put("clave", getClave());
////        r.put("rol", getRol());
////        
////        return r;
//   }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", clave=" + clave + ", rol=" + rol + '}';
    }
    
}

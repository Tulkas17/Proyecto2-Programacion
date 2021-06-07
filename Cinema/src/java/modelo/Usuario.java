package modelo;

//import org.json.JSONObject;
import java.io.Serializable;
import org.json.JSONObject;

public class Usuario implements Serializable {

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

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id_usuario", this.getId_usuario());
        j.put("clave", this.getClave());
        j.put("rol", this.getRol());
        return j;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.sql.SQLException;
import modelo.Cliente;
import modelo.Usuario;

/**
 *
 * @author Gaby
 */
public class CineBaseDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Usuario usuarioAux = new Usuario("admin","admin",1);
        UsuarioDao usuDao = new UsuarioDaoImpl();
//        usuDao.save(usuarioAux);
//        System.out.println(usuDao.findAll());
//        System.out.println(usuDao.findById("admin"));
//        usuarioAux.setRol(2);
//        usuDao.update(usuarioAux);
//        usuDao.delete(usuarioAux);
        
       Cliente clienteAux = new Cliente("1111","lopez","susana","2231-2345","33333",usuarioAux);
       ClienteDao clienteDao = new ClienteDaoImpl();
//       clienteDao.save(clienteAux);
//               System.out.println(clienteDao.findAll());
//        System.out.println(clienteDao.findById("22"));
//        System.out.println(clienteDao.findByIdUsuario("admin"));
        clienteAux.setId_cliente("33");
        clienteDao.update(clienteAux);
        clienteDao.delete(clienteAux);
       
    }
    
}

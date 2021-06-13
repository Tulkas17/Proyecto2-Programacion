function verificarUsuario() {
    var datos = new FormData();

    var id_cliente = document.getElementById("usuario").value;
    var clave = document.getElementById("password").value;

    var usuario = new Usuario(id_cliente, clave);
    datos.append("user", JSON.stringify(usuario));

    getJSON('ServicioLogin', datos, resultado);
}

function resultado(usuario) {

    if (usuario.result === "ok") {
        location.replace("index.jsp");  
    }else if(usuario.result === "bad"){
        document.getElementById('advertencia').innerHTML = "usuario no valido, revisar los credenciales";
        document.getElementById('botonRegistro').style.display = 'block';
    }
}



function Usuario(id_cliente, clave) {
    this.id_cliente = id_cliente;
    this.clave = clave;
}
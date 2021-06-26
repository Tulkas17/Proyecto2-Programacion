var mySessionStorage = window.sessionStorage;

function verificarUsuario() {
    var datos = new FormData();

    var id_cliente = document.getElementById("usuario").value;
    var clave = document.getElementById("password").value;

    var usuario = new Usuario(id_cliente, clave);
    datos.append("user", JSON.stringify(usuario));

    getJSON('ServicioLogin', datos, resultado);
}

function resultado(usuario) {
    mySessionStorage.setItem("user",usuario);
    if (usuario.result === "ok") {
        if (usuario.rol === 2) {
            location.replace("index.jsp");

        } else if (usuario.rol === 1) {
            location.replace("menuAdmin.jsp");
        }
    } else if (usuario.result === "bad") {
        document.getElementById('ocultar').style.display = 'block';
        document.getElementById('advertencia').innerHTML = "usuario no valido, revisar los credenciales";
    }
}

function registrar(){
    location.replace("registro.jsp");
}

function Usuario(id_cliente, clave) {
    this.id_cliente = id_cliente;
    this.clave = clave;
}
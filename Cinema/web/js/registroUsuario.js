function init() {
    console.log("Página inicializada..");
}

function registrarUsuario(id, clave, rol) {
    var datos = new FormData();

    datos.append("dato1", "Hola, ¿qué tal se encuentran hoy?");
    var registro = new Usuario(id, clave, rol);
    datos.append("user", JSON.stringify(registro));

    getJSON('ServicioRegistro', datos, mostrarResultados);

    agregarMensaje(`Datos enviados: ${JSON.stringify(registro)}`);
}


function mostrarResultados(datos) {
    console.log(datos);
    agregarMensaje(JSON.stringify(datos));
}

function borrarResultados() {
    var refM = document.getElementById('mensaje');
    refM.innerHTML = '<strong>Resultados:</strong><br />';
}

function agregarMensaje(msj) {
    var refM = document.getElementById('mensaje');
    refM.innerHTML = `${refM.innerHTML}${msj}<br />`;
}

function Usuario(id_usuario, clave, rol) {
    this.id_usuario = id_usuario;
    this.clave = clave;
    this.rol = rol;
}


function init() {
    console.log("Página inicializada..");
}

function registrarUsuario() {
    var datos = new FormData();

    var id_cliente = document.getElementById("id_cliente").value;
    var apellidos = document.getElementById("apellidos").value;
    var nombre = document.getElementById("nombre").value;
    var telefono = document.getElementById("telefono").value;
    var tarjeta_pago = document.getElementById("tarjeta_pago").value;
    var clave = document.getElementById("clave").value;

    var registro = new Cliente(id_cliente, apellidos, nombre, telefono, tarjeta_pago, clave);
    datos.append("user", JSON.stringify(registro));

    getJSON('ServicioRegistro', datos, mostrarResultados);

    location.replace("index.jsp");
}


function mostrarResultados(datos) {
    //console.log(datos);
    //agregarMensaje(JSON.stringify(datos));
}

function borrarResultados() {
    var refM = document.getElementById('mensaje');
    refM.innerHTML = '<strong>Resultados:</strong><br />';
}

function agregarMensaje(msj) {
    var refM = document.getElementById('mensaje');
    refM.innerHTML = `${refM.innerHTML}${msj}<br />`;
}

function Cliente(id_cliente, apellidos, nombre, telefono, tarjeta_pago, clave) {
    this.id_cliente = id_cliente;
    this.apellidos = apellidos;
    this.nombre = nombre;
    this.telefono = telefono;
    this.tarjeta_pago = tarjeta_pago;
    this.clave = clave;
}


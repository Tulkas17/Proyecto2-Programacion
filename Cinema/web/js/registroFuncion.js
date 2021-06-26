

var catalogo = null;
var catalogoPeli = null;
var cineSeleccionado = null;
var peliculaSeleccionada = null;
var salaSeleccionada = null;

function init() {

    actualizarMenuSala();
    actualizarMenuPelicula();

    console.log("Aplicación inicializada..");
}

function inicializarDatos(nuevosDatos, peliDatos) {


    catalogo = nuevosDatos;
    catalogoPeli = peliDatos;
    console.log("Datos cargados..");
}



//Sala
function actualizarMenuSala() {
    var refMenu = document.getElementById("menuSalas");
    if (refMenu) {
        refMenu.options.length = 0;

        {
            var opc = document.createElement("OPTION");
            opc.setAttribute("value", "null");
            opc.appendChild(document.createTextNode("(Seleccione la sala)"));
            refMenu.appendChild(opc);
        }

        var salas = catalogo["lista-salas"];

        salas.forEach(
                (sala, i) =>
        {

            var opc = document.createElement("OPTION");
            opc.setAttribute("value", sala.numero);
            opc.appendChild(document.createTextNode(sala.numero));
            refMenu.appendChild(opc);

        }
        );

        refMenu.selectedIndex = 0;
        salaSeleccionada = null;

    }
}

function seleccionarSala() {
    var refMenu = document.getElementById("menuSalas");
    if (refMenu) {
        var numero_sala = refMenu.value;
        if (numero_sala !== "null") {
            console.log("Seleccionando sala: " + numero_sala);
            /* global */ //cineSeleccionado = obtenerCine(id_cinema);
            localStorage.setItem("numero_sala", obtenerSala(numero_sala));
        } else {
            salaSeleccionada = null;
            console.error("No se ha seleccionado la sala.");
        }

    }
}

function obtenerSala(numero_sala) {
    var r = null;
    var salas = catalogo["lista-salas"];
    for (let i in salas) {
        if (salas[i].numero === numero_sala) {
            r = [i];
            break;
        }
    }
    return r;
}

function actualizarMenuPelicula() {
    var refMenu = document.getElementById("menuPeliculas");
    if (refMenu) {
        refMenu.options.length = 0;

        {
            var opc = document.createElement("OPTION");
            opc.setAttribute("value", "null");
            opc.appendChild(document.createTextNode("(Seleccione la pelicula)"));
            refMenu.appendChild(opc);
        }

        var peliculas = catalogoPeli["lista-peliculas"];
        var s = "-";
        peliculas.forEach(
                (pelicula, i) =>
        {

            var opc = document.createElement("OPTION");
            opc.setAttribute("value", pelicula.id_pelicula + s + pelicula.titulo);
            opc.appendChild(document.createTextNode(pelicula.id_pelicula + s + pelicula.titulo));
            refMenu.appendChild(opc);

        }
        );

        refMenu.selectedIndex = 0;
        salaSeleccionada = null;

    }
}
function seleccionarPelicula() {
    var refMenu = document.getElementById("menuPeliculas");
    if (refMenu) {
        var id_pelicula = refMenu.value;
        if (id_pelicula !== "null") {
            console.log("Seleccionando pelicula: " + id_pelicula);
            /* global */ //cineSeleccionado = obtenerCine(id_cinema);
            localStorage.setItem("id_pelicula", obtenerPelicula(id_pelicula));
        } else {
            peliculaSeleccionada = null;
            console.error("No se ha seleccionado la pelicula.");
        }

    }
}

function obtenerPelicula(id_pelicula) {
    var r = null;
    var peliculas = catalogoPeli["lista-peliculas"];
    for (let i in peliculas) {
        if (peliculas[i].id_pelicula === id_pelicula) {
            r = [i];
            break;
        }
    }
    return r;
}

function registrarFuncion() {
    var datos = new FormData();



    var fecha = document.getElementById("date").value;
    seleccionarSala();
    var sala_numero = document.getElementById("menuSalas").value;
    seleccionarPelicula();
    var pelicula_id = document.getElementById("menuPeliculas").value;

    console.log(`${fecha}`);
    console.log(`${sala_numero}`);
    console.log(`${pelicula_id}`);
    var Registrofuncion = new Funcion(sala_numero, fecha, pelicula_id);
    datos.append("funcion", JSON.stringify(Registrofuncion));
    getJSON('ServicioRegistroFuncion', datos);

    //location.replace("index.jsp");
}

function Funcion(sala_numero, fecha, pelicula_id) {
    this.sala_numero = sala_numero;
    this.fecha = fecha;
    this.pelicula = pelicula_id;
}







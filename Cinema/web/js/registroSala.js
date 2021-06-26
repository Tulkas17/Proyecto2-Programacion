/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var catalogo = null; // Mantiene el inventario de productos disponible.
var cineSeleccionado = null;

function initSala() {


    actualizarMenu();
   

    console.log("Aplicación inicializada..");
}

function inicializarDatos(nuevosDatos) {


    catalogo = nuevosDatos;
    console.log("Datos cargados..");
}


function actualizarMenu() {
    var refMenu = document.getElementById("menuCines");
    if (refMenu) {
        refMenu.options.length = 0;

        {
            var opc = document.createElement("OPTION");
            opc.setAttribute("value", "null");
            opc.appendChild(document.createTextNode("(Seleccione el cine)"));
            refMenu.appendChild(opc);
        }

        var cines = catalogo["lista-cines"];

        cines.forEach(
                (cine, i) =>
        {
           
                var opc = document.createElement("OPTION");
                opc.setAttribute("value", cine.id_cinema);
                opc.appendChild(document.createTextNode(cine.id_cinema));
                refMenu.appendChild(opc);

        }
        );

        refMenu.selectedIndex = 0;
        cineSeleccionado = null;
    
    }
}

function seleccionarCine() {
    var refMenu = document.getElementById("menuCines");
    if (refMenu) {
        var id_cinema = refMenu.value;
        if (id_cinema !== "null") {
            console.log("Seleccionando cine: " + id_cinema);
            /* global */ //cineSeleccionado = obtenerCine(id_cinema);
            localStorage.setItem("cinema_id", obtenerCine(id_cinema));
        } else {
            cineSeleccionado = null;
            console.error("No se ha seleccionado el cine.");
        }

    }
}
  
  function obtenerCine(id_cinema) {
    var r = null;
    var cines = catalogo["lista-cines"];
    for (let i in cines) {
        if (cines[i].id_cinema === id_cinema) {
            r = cines[i];
            break;
        }
    }
    return r;
}


function registrarSala() {
    var datos = new FormData();

    var numero = document.getElementById("numero").value;
    var capacidad = document.getElementById("capacidad").value;
    //var cinema_id = cineSeleccionado;
    seleccionarCine();
    var cinema_id = document.getElementById("menuCines").value;
    
    console.log(`${cinema_id}`);
    var registro = new Sala(numero, capacidad, cinema_id);
    datos.append("sala", JSON.stringify(registro));

    getJSON('ServicioSala', datos);

    document.getElementById("numero").value = "";
    document.getElementById("capacidad").value = "";
    document.getElementById("menuCines").value = "";
    alert("Sala resgistrada con exito en la base de datos");

}

function Sala(numero, capacidad, cinema_id) {
  this.numero = numero;
  this.capacidad = capacidad;
  this.cinema_id = cinema_id;
}






var t = new tablero(8, 8); // backbean
var myStorage = window.localStorage;

let posicionesFinal = [];
let posicionesCargadas = [];
let posiciones = [];

let clienteCargadas = [];
let funcionesCargadas = [];

var hoy = new Date();

let total = 0;

function init() {
    //console.log(t); 
    console.log('Asientos inicializada..');
    recargarAsientos();
    recargarClientes();
    //console.log('Funciones inicializada..');
    recargarFuncion();
}

function Asiento(funcion_sala_cinema_id, funcion_sala_numero, funcion_fecha, fila, columna, ocupado) {

    this.funcion_sala_cinema_id = funcion_sala_cinema_id;
    this.funcion_sala_numero = funcion_sala_numero;
    this.funcionfecha = funcion_fecha;
    this.fila = fila;
    this.columna = columna;
    this.ocupado = ocupado;
}

function Factura(id_cliente, fecha, pelicula, numero, sala_cinema_id, posiciones) {
    this.id_cliente = id_cliente;
    this.fecha = fecha;
    this.pelicula = pelicula;
    this.numero = numero;
    this.sala_cinema_id = sala_cinema_id;
    this.posiciones = posiciones;
}

function Cliente(id_cliente, apellidos, nombre, telefono, tarjeta_pago) {
    this.id_cliente = id_cliente;
    this.apellidos = apellidos;
    this.nombre = nombre;
    this.telefono = telefono;
    this.tarjeta_pago = tarjeta_pago;
}

function Tiquete(id_tiquete, factura_seq, asiento_funcion_sala_cinema, asiento_funcion_sala_numero, asiento_funcion_fecha, asiento_funcion_fila, asiento_funcion_columna, monto) {

    this.id_tiquete = id_tiquete;
    this.factura_seq = factura_seq;
    this.asiento_funcion_sala_cinema = asiento_funcion_sala_cinema;
    this.asiento_funcion_sala_numero = asiento_funcion_sala_numero;
    this.asiento_funcion_fecha = asiento_funcion_fecha;
    this.asiento_funcion_fila = asiento_funcion_fila;
    this.asiento_funcion_columna = asiento_funcion_columna;
    this.monto = monto;
}

function Funcion(sala_cinema_id, sala_numero, fecha, pelicula_id) {
    this.sala_cinema_id = sala_cinema_id;
    this.sala_numero = sala_numero;
    this.fecha = fecha;
    this.pelicula_id = pelicula_id;
}

function registraFactura() {
    var datos = new FormData();

    var fecha = document.getElementById("fecha").value;
    var cliente_id = document.getElementById("cliente_id").value;
    var tarjeta_pago = document.getElementById("tarjeta_pago").value;
    var total = document.getElementById("total").value;

    var registro = new Factura(fecha,
            cliente_id,
            tarjeta_pago,
            total);
    datos.append("facturaR", JSON.stringify(registro));

    getJSON('ServicioRegistroFactura', datos, mostrarResultados);
    location.replace("compraTiquetes.jsp");
}

function registraTiquete() {
    var datos = new FormData();
    var id_tiquete = document.getElementById("id_tiquete").value;
    var factura_seq = document.getElementById("factura_seq").value;
    var asiento_funcion_sala_cinema = document.getElementById("asiento_funcion_sala_cinema").value;
    var asiento_funcion_sala_numero = document.getElementById("asiento_funcion_sala_numero").value;
    var asiento_funcion_fecha = document.getElementById("asiento_funcion_fecha").value;
    var asiento_funcion_fila = document.getElementById("asiento_funcion_fila").value;
    var asiento_funcion_columna = document.getElementById("asiento_funcion_columna").value;
    var monto = document.getElementById("monto").value;

    var registro = new Tiquete(id_tiquete,
            factura_seq,
            asiento_funcion_sala_cinema,
            asiento_funcion_sala_numero,
            asiento_funcion_fecha,
            asiento_funcion_fila,
            asiento_funcion_columna,
            monto);
    datos.append("tiqueteR", JSON.stringify(registro));

    getJSON('ServicioRegistroTiquete', datos, mostrarResultados);
    location.replace("compraTiquete.jsp");
}

function recargarAsientos() {
    fetch('ServicioCargarAsientos').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        cargarAsientos(datos['datos_asientos']);
    });
}

function cargarAsientos(datos) {
    console.log(datos);
    datos.forEach(ele => {
        //console.log(myStorage.getItem('numero') + " : " + ele.funcion_sala_numero + " y " + myStorage.getItem('fecha') + " : " + ele.funcion_fecha);
        if (myStorage.getItem('numero') == ele.funcion_sala_numero
                && myStorage.getItem('fecha') == ele.funcion_fecha) {
            //console.log("aqui se creo 1");
            posicionesCargadas.push(new Asiento(
                    ele.funcion_sala_cinema_id,
                    ele.funcion_sala_numero,
                    ele.funcion_fecha,
                    ele.fila,
                    ele.columna,
                    ele.ocupado));
        }
    });
    construirTablero(t);
}

function recargarClientes() {
    fetch('ServicioCargarClientes').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        cargaClientes(datos['datos_cliente']);
    });
}

function cargaClientes(datos) {
    datos.forEach(ele => {
        //tiene que recibir  de una funcion y comparar los elementos
        //aux = new Funcion(1, 1, "2020-06-10T00:00", 181812);        
        clienteCargadas.push(new Cliente(
                ele.id_cliente,
                ele.apellidos,
                ele.nombre,
                ele.telefono,
                ele.tarjeta_pago));

    });
    console.log(clienteCargadas);
}

function recargarFuncion() {
    fetch('ServicioCargarFunciones').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        cargaFuncion(datos['datos_funciones']);
    });
}

function cargaFuncion(datos) {
    datos.forEach(ele => {
        funcionesCargadas.push(new Funcion(
                ele.sala_cinema_id,
                ele.sala_numero,
                ele.fecha,
                ele.pelicula_id));

    });
    console.log(funcionesCargadas);
}

function registraAsiento() {

    var datos = new FormData();

    var funcion_sala_numero = document.getElementById("funcion_sala_numero").value;
    var funcionfecha = document.getElementById("funcionfecha").value;
    var funcion_sala_numero = document.getElementById("funcion_sala_numero").value;
    var fila = document.getElementById("fila").value;
    var columna = document.getElementById("columna").value;

    var registro = new Asiento(1, funcion_sala_numero, funcionfecha, fila, columna, 1);
    datos.append("asientoR", JSON.stringify(registro));

    getJSON('ServicioRegistroAsiento', datos, mostrarResultados);
    location.replace("asinetoTest.jsp");
}

function construirTablero(t) {
    var refSeccion = document.getElementById('seccionTabla');
    refSeccion.innerHTML ="";
    //comparar elemento de funcion con asinetos

    if (refSeccion) {
        var tabla = document.createElement('TABLE');
        tabla.setAttribute('class', 'tablero');
        tabla.appendChild(document.createElement('THEAD'));
        var contenido = document.createElement('TBODY');
        var fila1;


        for (let i = 0; i < t.m(); i++) {
            fila1 = document.createElement('TR');
            var celda;
            for (let j = 0; j < t.n(); j++) {
                celda = document.createElement('TD');
                var btn = document.createElement('BUTTON');
                btn.setAttribute('type', 'button');
                btn.setAttribute('class', 'btnTablero');
                btn.setAttribute('name', 'posicion');
                btn.setAttribute('valor_fila', i);
                btn.setAttribute('valor_columna', j);

                btn.addEventListener('click', (evt) => {

                    var f = evt.currentTarget.getAttribute('valor_fila');
                    var c = evt.currentTarget.getAttribute('valor_columna');
                    if (buscarRepetido(f, c)) {
                        console.log({f: f, c: c});
                        hoy = new Date();
                        posiciones.push(new Asiento
                                (1, 1, obtenerFecha(), f, c, 1));
                    }
                });
                btn.innerHTML = '<div  id="seat" class="seat"></div>';

                posicionesCargadas.forEach(ele => {
                    console.log(i + " : " + (ele.fila - 48) + " y " + j + " : " + ele.columna);
                    if (i == (ele.fila - 48) && j == ele.columna) {
                        console.log("aqui estoy");
                        btn.innerHTML = '<div id="seat" class="seat occupied"></div>';
                    }
                });

                celda.appendChild(btn);
                fila1.appendChild(celda);
            }
            contenido.appendChild(fila1);
        }

        tabla.appendChild(contenido);
        refSeccion.appendChild(tabla);


        //CAMBIO COLOR SELECIONADO
        var count = 0;
        var seats = document.getElementsByClassName("seat");
        for (var i = 0; i < seats.length; i++) {
            var item = seats[i];

            item.addEventListener("click", (event) => {
                if (!event.target.classList.contains('occupied') && !event.target.classList.contains('selected')) {
                    count++;
                    event.target.classList.add("selected");
                }
            });

        }
    }
}

function obtenerFecha() {
    var fecha = hoy.getFullYear() + '-' + (hoy.getMonth() + 1) + '-' + hoy.getDate();
    var hora = hoy.getHours() + ':' + hoy.getMinutes() + ':' + hoy.getSeconds();

    return (fecha + " " + hora);
}

function buscarRepetido(f, c) {
    for (let i in posiciones) {
        console.log(posiciones[i].fila + " " + f);
        if (posiciones[i].fila === f && posiciones[i].columna === c) {
            return false;
        }
    }
    return true;
}

function removeDuplicates(data) {
    /*for (let i in posiciones) {
     for (let j in posiciones) {
     if (posiciones[i] === posiciones[j] && i !== j) {
     delete posiciones[j];
     posiciones[j] = null;
     }
     }
     }*/
    let result = data.filter((item, index) => {
        return data.indexOf(item) === index;
    })
    console.log(result);
    posiciones = result;
    return result;
}

function comprar(fecha, pelicula, numero, sala_cinema_id, id_cliente, id_rol) {
    console.log(fecha + " " + pelicula + " " + numero, +" " + sala_cinema_id, +" " + id_cliente);
    if (id_cliente !== "noUsuario"){
        if(id_rol == 1){
            window.alert("Los administradores no pueden comprar tiquetes");
        }
        if (id_rol == 2) {
            console.log(id_rol);
            posicionesFinal.push(posiciones);

            var count = 0;
            var seats = document.getElementsByClassName("seat");
            for (var i = 0; i < seats.length; i++) {
                var item = seats[i];

                if (item.classList.contains('selected')) {
                    count++;
                    item.classList.add("occupied");
                }

            }

            var total = count * 3500;
            var data = new FormData();

            var factura = new Factura(id_cliente, fecha, pelicula, numero, sala_cinema_id, posiciones);
            data.append("factura", JSON.stringify(factura));

            getJSON('ServicioAgregarFactura', data, mostrarResultados);   
        }
    }else if(id_cliente == "OnoUsuario") {
         window.alert("No se ha iniciad sesion");
    }
}

function mostrarResultados() {}

function mostrarDatos(t) {
    var refDatos = document.getElementById('datos');
    if (refDatos) {
        while (refDatos.firstChild) {
            refDatos.removeChild(refDatos.firstChild);
        }

        var m = String.fromCharCode(215);
        var p;
        p = document.createElement('P');
        p.textContent = `${t.m()} ${m} ${t.n()}; k = ${t.k}`;
        refDatos.appendChild(p);

        for (let i = 0; i < t.m(); i++) {
            p = document.createElement('P');
            var linea = "";
            for (let j = 0; j < t.n(); j++) {
                linea += t.estado[i][j] + ', ';
            }
            p.textContent = linea;
            refDatos.appendChild(p);
        }
    }
}

window.onload = init;

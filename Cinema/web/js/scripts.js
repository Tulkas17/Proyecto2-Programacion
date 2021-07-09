
var dimensionTabla = {filas: 4, columnas: 6};
var posterUrl = "https://www.themoviedb.org/t/p/w1280";
var myStorage = window.localStorage;
var col = 0;
var fil = 0;

function init() {
    if (myStorage.getItem('funciones') === null) {
        cargarFuncionesDB();
    }
    cargarCarteleraDB();
    fil = 0;
    col = 0;
}

function cargarCarteleraDB() {
    fetch('ServicioCargarPeliculas').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        cargarCartelera(datos['datos_pelicula'], 'galeria');
    });
}

function cargarFuncionesDB() {
    fetch('ServicioCargarFunciones').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        myStorage.setItem('funciones', JSON.stringify(datos));
    });
}

function cargarCartelera(ids, element) {
    cargarFuncionesDB();
    var key = "4c74316e5e640d2c8d8a6751a9ad2afe"; // (debe sustituirla por la clave provista por TMDB)

    var ref = document.getElementById(element);
    if (ref) {
        var m = ref.rows.length;
        var n = ref.rows[0].cells.length;

        console.assert(m === dimensionTabla.filas);
        console.assert(n === dimensionTabla.columnas);

        var p = 0;
        var movieDataURL = "https://api.themoviedb.org/3/movie/";
        for (let i in ids) {
            var id = ids[i].id_pelicula;
            fetch(`${movieDataURL}${id}?api_key=${key}`).then(
                    resultado => resultado.json()
            ).then(info => {
                actualizar(ref, info, m, n, p);
                p++;
            });
        }
    }
}

function actualizar(ref, info, m, n, p) {
    if (col < 6) {
        var celda = ref.rows[fil].cells[col];
        col++;
    } else {
        fil++;
        col = 0;
        var celda = ref.rows[fil].cells[col];
        col++;
    }

    //var celda = ref.rows[r].cells[c];

    var datosFunc = JSON.parse(localStorage.getItem("funciones")).datos_funciones;
    if (info.id > 0) {
        console.log(`${p}: ${info.title}`);
        t = `<p>${info.title}</p>`;
        t += `<img src='${posterUrl}${info.poster_path}' `;
        t += `alt='r${info.poster_path}' /><br>`;
        t += `<div class="comboFuncion">`;
        t += `<ul>`;
        for (let i in datosFunc) {
            if (datosFunc[i].pelicula_id == info.id) {
                t += `
                    <form action="compraTiquetes.jsp" method="get">
                    <input type="hidden" name="pelicula" value="${datosFunc[i].pelicula_id}" />
                    <input type="hidden" name="fecha" value="${datosFunc[i].fecha}" />
                    <input type="hidden" name="numero" value="${datosFunc[i].sala_numero}" />
                    <input type="hidden" name="sala_cinema_id" value="${datosFunc[i].sala_cinema_id}" />
                    <input type="hidden" name="sala_cinema_direccion" value="${datosFunc[i].sala_cinema_direccion}" />
                    <input type="submit" value="${datosFunc[i].fecha} ${"sala "}:  ${datosFunc[i].sala_numero}" />
                   </form>
                    `;
                console.log(datosFunc[i].sala_cinema_direccion);
            }
            
        }

        t += `</ul>`;
        t += `</div>`;
        celda.innerHTML = t;
    }
}

function cargarFuncionLocal() {
    document.getElementById("guardarAqui").innerHTML = JSON.parse(localStorage.getItem("funcionSelecionada"));
}

function guardarFuncionLocal(funcion) {
    console.log(`${funcion}`);
    myStorage.setItem('funcionSelecionada', JSON.stringify(funcion));
}

// Registro de peliculas
function registrarPelicula() {
    var datos = new FormData();

    var codigoPelicula = document.getElementById("codigoPelicula").value;

    if (codigoPelicula.length > 1) {
        var registro = new Pelicula(codigoPelicula);
        datos.append("pelicula", JSON.stringify(registro));

        getJSON('ServicioRegistroPelicula', datos);

        document.getElementById("codigoPelicula").value = "";
        alert("Pelicula resgistrada con exito en la base de datos");
    }
}

function Pelicula(codigoPelicula) {
    this.codigoPelicula = codigoPelicula;

}

function guardar(pelicula, fecha, numero) {
    myStorage.setItem("pelicula", pelicula);
    myStorage.setItem("fecha", fecha);
    myStorage.setItem("numero", numero);
}
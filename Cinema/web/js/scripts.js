function busquedaPelicula() {

}

var dimensionTabla = {filas: 2, columnas: 6};
var posterUrl = "https://www.themoviedb.org/t/p/w1280";

function init() {
    cargarCarteleraDB();

    console.log("Aplicación inicializada..");
}

function cargarCarteleraDB() {
    fetch('ServicioCargarPeliculas').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        cargarCartelera(datos['datos_pelicula'], 'galeria');
    });
}

function cargarCartelera(ids,element) {
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
                console.log(`${p}: ${info.title}`);
                actualizar(ref, info, m, n, p);
                p++;
            });
        }
    }
}

function actualizar(ref, info, m, n, p) {

    // Calcula la fila y columna correspondiente según
    // el valor de índice (posición) de la película en
    // el arreglo de ids.

    var r = p % m;
    var c = Math.floor(p / m);
    var celda = ref.rows[r].cells[c];

    t = `<img src='${posterUrl}${info.poster_path}' `;
    t += `alt='r${info.poster_path}' />`;
    t += `<p>${info.title}</p>`;
    celda.innerHTML = t;
}

window.onload = init;

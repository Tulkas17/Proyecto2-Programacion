function busquedaPelicula() {

}

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
                console.log(`${p}: ${info.title}`);
                actualizar(ref, info, m, n, p);
                p++;
            });
        }
    }
}

function actulizarIndex() {
    location.reload();
}

function actualizar(ref, info, m, n, p) {

    //var r = p % m;
    //var c = Math.floor(p / m);
    if (col === 6) {
        fil++;
        col = 0;
        var celda = ref.rows[fil].cells[col];
    } else {
        var celda = ref.rows[fil].cells[col];
        col++;
    }

    var datosFunc = JSON.parse(localStorage.getItem("funciones")).datos_funciones;


    if (info.id > 0) {
        t = `<p>${info.title}</p>`;
        t += `<img src='${posterUrl}${info.poster_path}' `;
        t += `alt='r${info.poster_path}' /><br>`;
        t += `<div class="comboFuncion">`;
        t += `<ul>`;
        for (let i in datosFunc) {
            console.log(`${datosFunc[i].pelicula_id}`);
            if (datosFunc[i].pelicula_id == info.id) {
                //t +=  `<l1><a href="registro.jsp">${datosFunc[i].fecha} ${"sala "}:  ${datosFunc[i].sala_numero}</a></l1>`;
                //t += `<a href='#' onclick='javascript:window.open("registro.jsp", "_blank", "scrollbars=1,resizable=1,height=300,width=450");' title='Pop Up'>${datosFunc[i].fecha} ${"sala "}:  ${datosFunc[i].sala_numero}</a>`;
                t += `<l1><a class="click" href="registro.jsp">${datosFunc[i].fecha} ${"sala "}:  ${datosFunc[i].sala_numero}</a></l1>`;
                //t += `<iframe class="modal" src="registro.jsp" style="position:fixed; top:0; left:0; bottom:0; right:0; width:0%; height:0%; border:none; margin:0; padding:0; overflow:hidden; z-index:999999;"></iframe>`;
            }
        }
        t += `</ul>`;
        t += `</div>`;
        celda.innerHTML = t;
    } else {
        t = `<img src="css/imagenes/proximamente.jpg">`;
        t += `<div class="comboFuncion">`;
        t += `<ul></ul>`;
        t += `</div>`;
        t += `<p></p>`;
        celda.innerHTML = t;
    }
}

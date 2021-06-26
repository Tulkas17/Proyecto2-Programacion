/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 //inicializar

 function init() {
    cargarFacturas();
    cargarHistorial();
    

    console.log("Aplicación inicializada..");
}
 
//constructor
function Factura(seq_factura,fecha,cliente_id,tarjeta_pago,total){
    this.seq_factura = seq_factura;
    this.cliente_id = cliente_id;
    this.fecha=fecha;
    this.tarjeta_pago = tarjeta_pago;
    this.total = total;
}


//fetch
function cargarFacturas(){
    fetch('ServicioListarFacturas').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        cargarTabla(datos['datos_Factura'], 'bt4');
    });
    
} 
function cargarHistorial(){
    fetch('ServicioListarHistorialFacturas').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        cargarTablaHistorial(datos['datos_Historial'], 'bt4');
    });
    
} 


function cargarTablaHistorial(datos, e) {
    var refTabla = document.getElementById(e);
    if (refTabla) {

        datos.forEach((fila ) => {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.seq_factura;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.fecha;
            nuevaCelda.setAttribute('class', 'd2');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.cliente_id;
            nuevaCelda.setAttribute('class', 'd3');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.total;
            nuevaCelda.setAttribute('class', 'd4');
            
      
            
            
        });

    }
}


function cargarTabla(datos, e) {
    var refTabla = document.getElementById(e);
    if (refTabla) {

        datos.forEach((fila ) => {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.seq_factura;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.fecha;
            nuevaCelda.setAttribute('class', 'd2');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.cliente_id;
            nuevaCelda.setAttribute('class', 'd3');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.total;
            nuevaCelda.setAttribute('class', 'd4');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerHTML = 
                    `<form action = "ServicioImprimirPDF" method ="get">
                        <input type = "hidden" name = "seq_factura" value= "${fila.seq_factura}"/>
                        <input type = "submit" value= "Imprimir"/>
                        </form>`;
            
                  
                    
            nuevaCelda.setAttribute('class', 'd5');
            
      
            
            
        });

    }
}

window.onload = init;
         
               
      
            
            
       
    


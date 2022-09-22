var usuario = document.getElementById('usuario');
var password = document.getElementById('password');
var mensaje = document.getElementById('dato_invalido');

mensaje.style.color="red";



var form = document.getElementById('formulario');
   form.addEventListener('submit', function(evt){
    
    console.log('Enviando formulario.....');
    evt.preventDefault();

    var mensajeError = [];
    if (usuario.value===null || usuario.value==='') {
        mensajeError.push('Ingresa tu usuario');
    }

    if (password.value===null || password.value==='') {
        mensajeError.push('Ingresa tu password');
    }

    mensaje.innerHTML = mensajeError.join(', ');

   });
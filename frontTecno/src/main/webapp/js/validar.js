function validarExt(){
	var archivoInput = document.getElementById('archivoInput');
	var archivoRuta = archivoInput.value;
	var extPermitidas = /(.csv|.CSV)$/i;
	
	if(!extPermitidas.exec(archivoRuta)){
		alert('Asegurese de haber seleccionado un archivo CSV');
		archivoInput.value = '';	
		return false;
	}
}
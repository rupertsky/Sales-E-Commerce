package co.edu.unbosque.frontTecno;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ProductoJSON {

	private static URL url;
	private static String sitio = "http://localhost:8082/";	
	
	//*********Métodos que permite mostrar los registros de la tabla Clientes**************
	public static ArrayList<Productos> parsingProductos(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Productos> lista = new ArrayList<Productos>();
		JSONArray productos = (JSONArray) jsonParser.parse(json);
		Iterator i = productos.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Productos producto = new Productos();
			producto.set_id(innerObj.get("_id").toString());
			producto.setCodigo_producto(innerObj.get("codigo_producto").toString());			
			producto.setNombre_producto(innerObj.get("nombre_producto").toString());
			producto.setNit_proveedor(innerObj.get("nit_proveedor").toString());
			producto.setPrecio_compra(Double.parseDouble(innerObj.get("precio_compra").toString()));
			producto.setIva_compra(Double.parseDouble(innerObj.get("iva_compra").toString()));
			producto.setPrecio_venta(Double.parseDouble(innerObj.get("precio_venta").toString()));
			lista.add(producto);
		}
		return lista;
	}
	//*****************************************************************************
	
	//*********Método que consume la API de listar*********************************
	public static ArrayList<Productos> getJSON() throws IOException, ParseException {

		url = new URL(sitio + "api/productos/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Productos> lista = new ArrayList<Productos>();
		lista = parsingProductos(json);
		http.disconnect();
		return lista;
	}
	//******************************************************************************
	
	//*********Método que consume la API de Guardar*********************************
	public static int postJSON(Productos producto) throws IOException {

		url = new URL(sitio + "api/productos/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{" 
				+ "\"codigo_producto\":\"" + producto.getCodigo_producto()	
				+ "\",\"nombre_producto\": \""+ producto.getNombre_producto()		
				+ "\",\"nit_proveedor\": \"" + producto.getNit_proveedor() 
				+ "\",\"precio_compra\":\"" + producto.getPrecio_compra()
				+ "\",\"iva_compra\":\"" + producto.getIva_compra()
				+ "\",\"precio_venta\":\"" + producto.getPrecio_venta()
				+ "\"}";
		System.out.println(producto.getCodigo_producto());
		System.out.println(producto.getNombre_producto());
		System.out.println(producto.getNit_proveedor());
		System.out.println(producto.getPrecio_compra());
		System.out.println(producto.getIva_compra());
		System.out.println(producto.getPrecio_venta());
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	//*********************************************************************************
	
	//*********Método que consume la API de Actualizar*********************************
	public static int putJSON(Productos producto, String id) throws IOException {

		url = new URL(sitio + "api/productos/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"_id\":\"" + id 
				+ "\",\"codigo_producto\":\"" + producto.getCodigo_producto()	
				+ "\",\"nombre_producto\": \""+ producto.getNombre_producto()		
				+ "\",\"nit_proveedor\": \"" + producto.getNit_proveedor() 
				+ "\",\"precio_compra\":\"" + producto.getPrecio_compra()
				+ "\",\"iva_compra\":\"" + producto.getIva_compra()
				+ "\",\"precio_venta\":\"" + producto.getPrecio_venta() 
				+ "\"}"; 
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	//*******************************************************************************
	
	//*********Método que consume la API de Eliminar*********************************
	public static int deleteJSON(String id) throws IOException {

		url = new URL(sitio + "api/productos/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
}

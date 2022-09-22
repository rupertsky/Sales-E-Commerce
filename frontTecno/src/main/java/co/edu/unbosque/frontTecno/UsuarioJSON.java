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


public class UsuarioJSON {
	private static URL url;
	private static String sitio = "http://localhost:8081/";
	
	//*********Métodos que permite mostrar los registros de la tabla Usuarios**************
	public static ArrayList<Usuarios> parsingUsuarios(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		JSONArray usuarios = (JSONArray) jsonParser.parse(json);
		Iterator i = usuarios.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Usuarios usuario = new Usuarios();
			usuario.set_id(innerObj.get("_id").toString());
			usuario.setCedula_usuario(innerObj.get("cedula_usuario").toString());			
			usuario.setNombre_usuario(innerObj.get("nombre_usuario").toString());
			usuario.setEmail_usuario(innerObj.get("email_usuario").toString());
			usuario.setPassword(innerObj.get("password").toString());
			usuario.setUsuario(innerObj.get("usuario").toString());
			lista.add(usuario);
		}
		return lista;
	}
	//*****************************************************************************
	
	//*********Método que consume la API de listar*********************************
	public static ArrayList<Usuarios> getJSON() throws IOException, ParseException {
		
		url = new URL(sitio + "api/usuarios/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		
		ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		lista = parsingUsuarios(json);
		http.disconnect();
		return lista;
	}
	//*****************************************************************************
	
	//*********Método que consume la API de guardar*********************************
	public static int postJSON(Usuarios usuario) throws IOException {
		
		url = new URL(sitio + "api/usuarios/guardar");
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
				+ "\"cedula_usuario\":\"" + usuario.getCedula_usuario()				
				+ "\",\"nombre_usuario\": \"" + usuario.getNombre_usuario() 
				+ "\",\"email_usuario\": \""  + usuario.getEmail_usuario() 
				+ "\",\"password\":\"" + usuario.getPassword() 
				+ "\",\"usuario\":\"" + usuario.getUsuario() 
				+ "\"}";
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	//*****************************************************************************
	
	//*********Método que consume la API de Actualizar*********************************
	public static int putJSON(Usuarios usuario, String id) throws IOException {
		
		url = new URL(sitio + "api/usuarios/actualizar");
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
				+ "\",\"cedula_usuario\":\"" + usuario.getCedula_usuario()
				+ "\",\"nombre_usuario\": \"" + usuario.getNombre_usuario() 
				+ "\",\"email_usuario\": \"" + usuario.getEmail_usuario() 
				+ "\",\"usuario\":\"" + usuario.getUsuario() 
				+ "\",\"password\":\"" + usuario.getPassword() 
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
		
		url = new URL(sitio + "api/usuarios/eliminar/" + id);
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
	//*******************************************************************************
}
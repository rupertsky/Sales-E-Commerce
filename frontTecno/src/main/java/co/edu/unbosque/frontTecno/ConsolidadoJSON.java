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

public class ConsolidadoJSON {
	private static URL url;
	private static String sitio = "http://localhost:8084/";
	
public static int postJSON(Consolidado consolidado) throws IOException {
		
		url = new URL(sitio + "api/consolidacion/guardar");
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
				+ "\"fecha_venta\":\"" + consolidado.getFecha_venta()				
				+ "\",\"ciudad_venta\": \"" + consolidado.getCiudad_venta() 
				+ "\",\"total_ventas\": \""  + String.valueOf(consolidado.getTotal_ventas()) 
				+ "\"}";
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static ArrayList<Consolidado> parsingConsolidado(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Consolidado> lista = new ArrayList<Consolidado>();
		JSONArray consolidados = (JSONArray) jsonParser.parse(json);
		Iterator i = consolidados.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Consolidado consolidado = new Consolidado();
			consolidado.setCiudad_venta(innerObj.get("ciudad_venta").toString());
			consolidado.setFecha_venta(innerObj.get("fecha_venta").toString());
			consolidado.setTotal_ventas(Double.parseDouble(innerObj.get("total_ventas").toString()));
			lista.add(consolidado);
	}
	return lista;
	}
	
	public static ArrayList<Consolidado> getJSON() throws IOException, ParseException {

		url = new URL(sitio + "api/consolidacion/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Consolidado> lista = new ArrayList<Consolidado>();
		lista = parsingConsolidado(json);
		http.disconnect();
		return lista;
	}
}

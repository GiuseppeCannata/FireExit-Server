import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Mappa;
import entity.Nodo;
import model.Mappa_DB;
import model.Nodo_DB;
import model.Utente_DB;
import services.Mappa_service;

/**
 * Mappa_Resource offre metodi per il recupero delle informazioni del messaggio Json relative alla mappa 
 *
 */
@Path("maps")
public class Mappa_Resource {
	
	/*
	@POST
	@Path("controllaStatoEmergenza")
	@Consumes(MediaType.APPLICATION_JSON)
	public void controllaStatoEmergenza(String messaggio){
		
		HttpURLConnection connection = null;
		System.out.println("CONTROLLO");
		ArrayList<String> tokensList = new ArrayList<>();
		Utente_DB udb = new Utente_DB();
		tokensList = udb.getListToken();
		
		Iterator<String> it = tokensList.iterator();
		
		
		
	        URL url;
			try {
			
			url = new URL("https://fcm.googleapis.com/fcm/send");
			
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setRequestProperty("Authorization", "key=AIzaSyD7a0N56L8RoWSobOSQxvQ6GAnKT5aAkuE" );
	        connection.connect();
	        
	        JsonObject json = new JsonObject();

            json.addProperty("to", "eqK8YWzqLsI:APA91bF2RPxkM_sgeZQd0eTTEPSZLyYe8FKjq0lxxfhfWErKqkqh6vgYt0fzbaJcFuHHRriyRdQZfr-5zxE5KlwlcNwG-T6LC3ljuzW2gjGROxl_DfHhFntcagnErRaij0xqgt_0mTMA");


            JsonObject info = new JsonObject();
            info.addProperty("title", "TechnoWeb");   // Notification title
            info.addProperty("body", "Hello Test notification"); // Notification body

            json.add("notification", info);
            
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            connection.getInputStream();
            
	        
	        
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	            if (connection != null) {
	                try {
	                    connection.disconnect();
	                }

	                catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}*/

	@POST
	@Path("getMappa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Per il download della mappa da parte dell applicazione
	 * 
	 * @param macAdrs JSON contenente l'indirizzo MAC del nodo posizione utente
	 * @return JSON contente la mappa relativa al piano dell uttente
	 */
	public String downloadMappa(String macAdrs) {
		
		System.out.println("mac"+macAdrs);

		// Estrazione dal Json in entrata dell info macAdrs
		JsonObject jobj = new Gson().fromJson(macAdrs, JsonObject.class);
		String mac = jobj.get("mac_beacon").getAsString();

		Mappa_service mappaService = new Mappa_service();
		Nodo_DB ndb = new Nodo_DB();
		int piano;
		Mappa mappa;

		piano = ndb.findPianoByMAC(mac);
		mappa = mappaService.CostruzioneMappa(piano);

		// Costruisco il Json da inviare all App
		Gson gson = new Gson();
		String esito = gson.toJson(mappa);

		return esito;	//essendo un Json il ritorno sarà di tipo String	
	}

	@GET
	@Path("downloadPiantina/{nome}")
	@Produces("image/png")
	/**
	 * Per il download della piantina relativa alla mappa
	 * 
	 * @param nome JSON contenente il nome della risorsa da scaricare
	 * @return piantina relativa alla mappa
	 */
	public Response downloadPiantina(@PathParam("nome") String nome) {

		System.out.println("Nome piantina: "+nome);

		// Path all interno della cartella server dove sono contenute le mappe
		// Path assoluto poichè il relativo non vede la directory
		File f = new File("C:\\Users\\User\\Desktop\\glassfish5\\glassfish\\domains\\domain1\\docroot\\src\\images\\"+nome+".png");
		FileInputStream inStream = null;

		try {
			inStream = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ResponseBuilder response = Response.ok((Object)inStream);
		response.header("Content-Length", f.length());
		
		return response.build();	
	}

	@POST
	@Path("segnalazione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Consente di prendere in consegna la segnalazione fatta da un utente
	 * 
	 * @param NodiSottoIncendio JSON contenete i nodi segnalati dall utente
	 * @return JSON con il risulato della segnalazione (true/false)
	 */
	public String segnalazione(String NodiSottoIncendio) {

		System.out.println("mac"+NodiSottoIncendio);
		boolean esito = false;

		Type type = new TypeToken<ArrayList<Nodo>>() {
		}.getType();

		// Estrazione dell ArrayList inviato dall app
		ArrayList<Nodo> dati_nodi = new Gson().fromJson(NodiSottoIncendio, type);
		Mappa_service mappaService = new Mappa_service();

		esito = mappaService.prendiSegnalazione(dati_nodi);
		//System.out.println("esito update nodi: "+esito);
		
		JsonObject Data = new JsonObject();
		Data.addProperty("esito", esito);

		return Data.toString();		
	}

	@POST
	@Path("downloadAggiornamenti")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Per il download degli aggiornamenti relativi alla mappa
	 * 
	 * @param pianoJSON JSON relativo al piano dell utente
	 * @return JSON contenente la mappa aggiornata
	 */
	public String downloadAggiornamenti(String pianoJSON) {

		System.out.println("mac"+pianoJSON);

		// Estrazione dal Json in entrata dell info macAdrs
		JsonObject jobj = new Gson().fromJson(pianoJSON, JsonObject.class);
		int pianoUtente = jobj.get("PianoUtente").getAsInt();

		Mappa_service mappaService = new Mappa_service();
		Mappa mappa;

		mappa = mappaService.CostruzioneMappa(pianoUtente);

		// Costruisco il Json da inviare all App
		Gson gson = new Gson();
		String esito = gson.toJson(mappa);

		return esito;	//essendo un Json il ritorno sarà di tipo String	
	}
}

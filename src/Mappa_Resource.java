import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

import entity.Mappa;
import entity.Nodo;
import model.Mappa_DB;
import model.Nodo_DB;
import services.Mappa_service;
import utils.Parametri;

/**
 * Mappa_Resource offre metodi per il recupero delle informazioni del messaggio Json relative alla mappa 
 *
 */
@Path("maps")
public class Mappa_Resource {
	
	@POST
	@Path("inviaAlert")
	@Consumes(MediaType.APPLICATION_JSON)
	public void inviaAlert(String pianoJSON){
		
		System.out.println("Ricezione di invio Alert");
		
		JsonObject jobj = new Gson().fromJson(pianoJSON, JsonObject.class);
		int piano = jobj.get("piano").getAsInt();
		
		Mappa_service ms = new Mappa_service();
        Mappa_DB mdb = new Mappa_DB();
        
		mdb.updateStatoEmergenza(1,piano);
		ms.inviaAlert(/*piano*/);
	}

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
	public String downloadMappa(String macJSON) {
		
		System.out.println("MAC: "+macJSON);

		// Estrazione dal Json in entrata dell info macAdrs
		JsonObject jobj = new Gson().fromJson(macJSON, JsonObject.class);
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

		System.out.println("Nome piantina cercata: "+nome);

		//String curDir = System.getProperty("user.dir");
		//System.out.println(" - " + curDir);
		// Path all interno della cartella server dove sono contenute le mappe
		File f = new File("../docroot/src/images/"+nome+".png");
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
	public String segnalazione(String NodiSottoIncendioJSON) {

		System.out.println("NodiSottoIncendio: "+NodiSottoIncendioJSON);
		boolean esito = false;

		Type type = new TypeToken<ArrayList<Nodo>>() {
		}.getType();

		// Estrazione dell ArrayList inviato dall app
		ArrayList<Nodo> dati_nodi = new Gson().fromJson(NodiSottoIncendioJSON, type);
	
		int controllo = 0;
        int piano = dati_nodi.get(0).getmappaId();
		
		Nodo_DB ndb = new Nodo_DB();
		Mappa_DB mdb = new Mappa_DB();
		
		mdb.updateStatoEmergenza(1,piano);
	
		for(Nodo nodo: dati_nodi) 
			if(nodo.isTipoIncendio() && nodo.isTipoUscita()) {
				ndb.setTipo(Parametri.TIPO_USCITA_INCENDIATO, nodo.getBeaconId());
				controllo++;
			} else if(nodo.isTipoIncendio()) {
				ndb.setTipo(Parametri.TIPO_BASE_INCENDIATO, nodo.getBeaconId());
				controllo++;
			} else if(nodo.isCambiato() && nodo.isTipoUscita()) {
				ndb.setTipo(Parametri.TIPO_USCITA_NO_INCENDIATO, nodo.getBeaconId());
				controllo++;
			} else if(nodo.isCambiato()) {
				ndb.setTipo(Parametri.TIPO_BASE_NO_INCENDIATO, nodo.getBeaconId());
				controllo++;
			}

		if (controllo == dati_nodi.size())
			esito = true;
		
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

		System.out.println("Piano: "+pianoJSON);

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

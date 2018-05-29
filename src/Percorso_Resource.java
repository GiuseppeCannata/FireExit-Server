import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import services.Mappa_service;
import services.Percorso_service;

/**
 * Percorso_Resource offre metodi per il recupero delle informazioni del messaggio Json relative al
 * percorso 
 *
 */
@Path("percorso")
public class Percorso_Resource {


	@POST
	@Path("getPercorsoMinimo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Per il download del percorso nello stato di emergenza
	 * 
	 * @param messaggio contente la posizione dell utente e il piano del piano in cui si trova
	 * @return JSON relativo al percorso calcolato
	 */
	public String downloadPercorso(String messaggioJSON) {

		System.out.println("JSON p. emergenza: "+messaggioJSON);

		// Estrazione dal Json in entrata
		JsonObject jobj = new Gson().fromJson(messaggioJSON, JsonObject.class);
		String mac = jobj.get("posUtente").getAsString();  //relativo al MAC del beacon
		int piano = jobj.get("piano").getAsInt();

		Percorso_service percorsoService = new Percorso_service();
		Mappa_service mappaService = new Mappa_service();

		Mappa mappa = mappaService.CostruzioneMappa(piano);
		Nodo posU = percorsoService.creaNodo(mac);
		ArrayList<Arco> percorso = percorsoService.calcolaPercorsoEmergenza(mappa , posU);

		// Costruisco il Json da inviare all App
		Gson gson = new Gson();
		String esito = gson.toJson(percorso);

		System.out.println(esito);

		return esito;	//essendo un Json il ritorno sarà di tipo String	
	}
	
	@POST
	@Path("getPercorsoMinimoNormale")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Per il percorso in modalità normale
	 * 
	 * @param messaggio conmtente la posizione attuale dell utente, il piano, e il nodo destinazione
	 * @return JSON relativo al percorso calcolato
	 */
	public String downloadPercorsoMinimoNormale(String messaggioJSON) {

		System.out.println("JSON p. Normale: "+messaggioJSON);

		// Estrazione dal Json in entrata
		JsonObject jobj = new Gson().fromJson(messaggioJSON, JsonObject.class);
		String macPU = jobj.get("posUtente").getAsString();  //relativo al MAC del beacon
		String macDest = jobj.get("destinazione").getAsString();
		int piano = jobj.get("piano").getAsInt();

		Percorso_service percorsoService = new Percorso_service();
		Mappa_service mappaService = new Mappa_service();

		Mappa mappa = mappaService.CostruzioneMappa(piano);
		Nodo posU = percorsoService.creaNodo(macPU);
		Nodo destinazione = percorsoService.creaNodo(macDest);
		
		ArrayList<Arco> percorso = percorsoService.calcolaPercorsoNormale(mappa , posU, destinazione);

		// Costruisco il Json da inviare all App
		Gson gson = new Gson();
		String esito = gson.toJson(percorso);

		System.out.println(esito);

		return esito;	//essendo un Json il ritorno sarà di tipo String	
	}
}

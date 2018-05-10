import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entity.Mappa;
import model.Mappa_DB;
import model.Nodo_DB;
import model.Utente_DB;
import services.Mappa_service;


@Path("user")
public class Utente_Resource {
	
	@POST
	@Path("registrationToken")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Per la registrazione del token (identificativo dell app per ogni utente)
	 * 
	 * @param macAdrs JSON contenente il token 
	 */
	public void registrationToken(String JSONtoken) {
		
		System.out.println("JSONtoken"+JSONtoken);
		
		JsonObject jobj = new Gson().fromJson(JSONtoken, JsonObject.class);
		String token = jobj.get("token").getAsString();
		
		Utente_DB udb = new Utente_DB();
		
		udb.insert(token);
		
	}
	
	@POST
	@Path("getTokens")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Per ottenre la lista dei token
	 * 
	 * @return JSON relativo alla lista dei token
	 */
	public String getTokens() {
		
		Mappa_DB mdb = new Mappa_DB();
		String esito = null;
		
		if(!mdb.controllaStatoEmergenza()) {
			Utente_DB udb = new Utente_DB();
			ArrayList<String> ListToken;
			ListToken = udb.getListToken();
			Gson gson = new Gson();
			esito = gson.toJson(ListToken);	
		} else {
			ArrayList<String> ListToken = new ArrayList<String>();
			Gson gson = new Gson();
			esito = gson.toJson(ListToken);	
		}
		
		return esito;
		
	}
}

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Utente_DB;

/*
 * Utente_resource fornisce metodi per il recupero di informazioni del messaggio Json relative all utente
 */

@Path("user")
public class Utente_Resource {
	
	@POST
	@Path("registrationToken")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Per la registrazione del token (identificativo dell app per ogni utente)
	 * 
	 * @param JSONtoken JSON contenente il token 
	 */
	public String registrationToken(String tokenJSON) {
		
		System.out.println("JSONtoken: "+tokenJSON);
		Gson gson = new Gson();
		
		JsonObject jobj = gson.fromJson(tokenJSON, JsonObject.class);
		String token = jobj.get("token").getAsString();
		
		Utente_DB udb = new Utente_DB();
		
		return gson.toJson(udb.insert(token));		
	}
}

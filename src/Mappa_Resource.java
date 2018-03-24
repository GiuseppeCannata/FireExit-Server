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
import entity.NodoApp;
import services.Mappa_service;


@Path("maps")
public class Mappa_Resource {
	
	@POST
    @Path("getMappa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String downloadMappa(String macAdrs) {
		
		System.out.println("mac"+macAdrs);
		
		// Estrazione dal Json in entrata dell info macAdrs
		JsonObject jobj = new Gson().fromJson(macAdrs, JsonObject.class);
		String mac = jobj.get("mac_beacon").getAsString();
		
		Mappa_service mappaService = new Mappa_service();
		
		Mappa mappa;
		mappa = mappaService.CostruzioneMappaByMAC(mac);
		
		// Costruisco il Json da inviare all App
		Gson gson = new Gson();
		String esito = gson.toJson(mappa);
		
	    return esito;	//essendo un Json il ritorno sarà di tipo String	
	}
	
	@GET
    @Path("/downloadPiantina/{nome}")
	@Produces("image/png")
	public Response downloadPiantina(@PathParam("nome") String nome) {
		
		System.out.println("Nome piantina: "+nome);
		
		// Path all interno della cartella server dove sono contenute le mappe
		// Path assoluto poichè il relativo non vede la directory
		File f = new File("C:\\Users\\User\\Desktop\\glassfish5\\glassfish\\domains\\domain1\\docroot\\src\\images\\"+nome+".png");
	    FileInputStream inStream = null;
	    
		try {
			inStream = new FileInputStream(f);
		} 
		
		catch (FileNotFoundException e) {
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
	public String segnalazione(String NodiSottoIncendio) {
		
		System.out.println("mac"+NodiSottoIncendio);
		boolean esito = false;
		
		Type type = new TypeToken<ArrayList<NodoApp>>() {
        }.getType();
        
        // Estrazione dell ArrayList inviato dall app
        ArrayList<NodoApp> dati_nodi = new Gson().fromJson(NodiSottoIncendio, type);
        Mappa_service mappaService = new Mappa_service();
        
        esito = mappaService.prendiSegnalazione(dati_nodi);
        System.out.println("esito update nodi: "+esito);
        
        JsonObject Data = new JsonObject();
        Data.addProperty("esito", esito);
        
        return Data.toString();		
	}
}

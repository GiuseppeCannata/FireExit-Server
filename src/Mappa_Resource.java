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

import entity.Mappa;
import services.Mappa_service;




@Path("maps")
public class Mappa_Resource {
	
	@POST
    @Path("getMappa")
	@Consumes(MediaType.APPLICATION_JSON)
	public String downloadMappa(String macAdrs) {
		
		System.out.println("mac"+macAdrs);
		
		JsonObject jobj = new Gson().fromJson(macAdrs, JsonObject.class);
		String mac = jobj.get("mac_beacon").getAsString();
		
		Mappa_service mS = new Mappa_service();
		
		Mappa mappa;
		mappa = mS.CostruzioneMappaByMAC(mac);
		
		Gson gson = new Gson();
		String esito = gson.toJson(mappa);
		
	    return esito;		
	}
	
	@GET
    @Path("/downloadPiantina/{nome}")
	@Produces("image/png")
	public Response downloadPiantina(@PathParam("nome") String nome) {
		
		System.out.println("Nome piantina: "+nome);
		
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
	
	
	

}

package services;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import model.Arco_DB;
import model.Nodo_DB;
import model.Utente_DB;

/**
 * Mappa_service viene istanziata da Mappa_resorce.
 * Offre metodi per generare i dati che poi verranno inviati all utente
 */
public class Mappa_service {

	public Mappa_service() {

	}

	
	public void inviaAlert(/*int piano*/) {
		
		//Mappa_DB mdb = new Mappa_DB();
		HttpURLConnection connection = null;
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
	        String json = "";

            if(it.hasNext())
                json = "{\"registration_ids\": [\""+it.next()+"\"";
            while(it.hasNext())
                json = json+", \"" + it.next() + "\"";

            json = json + "], \"data\": {\"title\": \"FireExit - EMERGENZA INCENDIO\" , " +
                                         "\"message\": \"Clicca per metterti al sicuro\"}}";
            
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            connection.getInputStream();
            
           // mdb.updateStatoEmergenza(1,piano);
	        
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
	}

	/**
	 * Gestisce le segnalazioni di emergenza
	 * 
	 * @param nodi List relativa ai nodi sotto incendio
	 * @return true/false se la segnalazione è stata o meno effettuata
	 */
	/*public boolean prendiSegnalazione(ArrayList<Nodo> Nodi) {

		boolean esito = false;
		int controllo = 0;
        int piano = Nodi.get(0).getmappaId();
		
		Nodo_DB ndb = new Nodo_DB();
		Mappa_DB mdb = new Mappa_DB();
		
		mdb.updateStatoEmergenza(1,piano);
	
		for(Nodo nodo: Nodi) 
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

		if (controllo == Nodi.size())
			esito = true;

		return esito;		
	}*/

	/**
	 * Pemette di costruire la mappa
	 * 
	 * @param piano relativo alla mappa che si desidera ottenre
	 * @return mappa richiesta
	 */
	public Mappa CostruzioneMappa(int piano) {

		Nodo_DB ndb = new Nodo_DB();
		Arco_DB adb = new Arco_DB();

		ArrayList<Nodo> Nodi = new ArrayList<Nodo>();
		ArrayList<Arco> Archi = new ArrayList<Arco>();

		ndb.findNodiByPiano(Nodi, piano);  //prende i nodi relativi al piano
		Archi = adb.findArchiByPiano(piano);

		return new Mappa(piano , "map"+piano , Nodi, Archi);	
	}
}

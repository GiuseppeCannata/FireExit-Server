package services;

import java.util.ArrayList;

import entity.Mappa;
import entity.Nodo;
import entity.NodoApp;
import model.Nodo_DB;
import utils.Parametri;

public class Mappa_service {
	
	
	public Mappa_service() {
		
	}
	
	
	public Mappa CostruzioneMappaByMAC(String mac) {
		
		Nodo_DB ndb = new Nodo_DB();
		int piano;
		
		ArrayList<Nodo> Nodi = new ArrayList<Nodo>();
		
		
		
		// in base al macAdrs agganciato dall app, vedo il beacon a che piano corrisponde
		piano = ndb.FindPianoByID(mac);
		// prelevo i beacon relativi al piano
		ndb.FindNodiByPiano(Nodi, piano);
		
		// costruisco la mappa
		Mappa mappa = new Mappa(piano , "map"+piano , Nodi);
		
		return mappa;	
		 
	}
	

	public boolean prendiSegnalazione(ArrayList<NodoApp> Nodi) {
		
		boolean esito = false;
		int controllo = 0;
		
		Nodo_DB ndb = new Nodo_DB();
		
		for(NodoApp n: Nodi) 
			if(n.isTipoIncendio() & ndb.setTipo(Parametri.TIPO_INCENDIO, n.getBeaconId())) 
				controllo++;
			
		if (controllo == Nodi.size())
			esito = true;
		
		return esito;	
		
	}

}

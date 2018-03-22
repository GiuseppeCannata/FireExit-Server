package services;



import java.util.ArrayList;

import entity.Mappa;
import entity.Nodo;
import model.Nodo_DB;

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

}

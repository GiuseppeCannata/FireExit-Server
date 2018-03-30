package services;

import java.util.ArrayList;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import model.Arco_DB;
import model.Nodo_DB;
import utils.Parametri;

public class Mappa_service {
	
	
	public Mappa_service() {
		
	}
	
	
    /*
     * Gestisce le segnalazioni di emergenza
     * 
     */
	public boolean prendiSegnalazione(ArrayList<Nodo> Nodi) {
		
		boolean esito = false;
		int controllo = 0;
		
		Nodo_DB ndb = new Nodo_DB();
		
		for(Nodo n: Nodi) {
			
			if(n.isTipoIncendio()) {
				
				ndb.setTipo(Parametri.TIPO_INCENDIO, n.getBeaconId());
				controllo++;
				
			} else if(n.isCambiato()) {
				
				ndb.setTipo(Parametri.TIPO_BASE, n.getBeaconId());
				controllo++;
				
			}
			
		}
			
		if (controllo == Nodi.size())
			esito = true;
		
		return esito;		
	}
	
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

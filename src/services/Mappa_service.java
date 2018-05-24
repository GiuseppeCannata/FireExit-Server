package services;

import java.util.ArrayList;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import model.Arco_DB;
import model.Mappa_DB;
import model.Nodo_DB;
import utils.Parametri;

/**
 * Mappa_service viene istanziata da Mappa_resorce.
 * Offre metodi per generare i dati che poi verranno inviati all utente
 */
public class Mappa_service {

	public Mappa_service() {

	}


	/**
	 * Gestisce le segnalazioni di emergenza
	 * 
	 * @param nodi List relativa ai nodi sotto incendio
	 * @return true/false se la segnalazione è stata o meno effettuata
	 */
	public boolean prendiSegnalazione(ArrayList<Nodo> Nodi) {

		boolean esito = false;
		int controllo = 0;
        int piano = Nodi.get(0).getmappaId();
		
		Nodo_DB ndb = new Nodo_DB();
		Mappa_DB mdb = new Mappa_DB();

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

		if (controllo == Nodi.size() && mdb.updateStatoEmergenza(1, piano))
			esito = true;

		return esito;		
	}

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

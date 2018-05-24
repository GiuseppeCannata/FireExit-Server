package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import model.Nodo_DB;

/**
 * Percorso_service viene istanziata da Mappa_resorce.
 * Offre metodi per generare i dati che poi verranno inviati all utente
 */
public class Percorso_service {


	public Percorso_service() {

	}

	/**
	 * Pemette di ricavare il nodo dal suo indirizzo MAC
	 * 
	 * @param mac relativo al nodo che si desidera avere
	 * @return nodo cercato
	 */
	public Nodo creaNodo(String mac) {

		Nodo_DB ndb = new Nodo_DB();

		return ndb.FindNodoByMac(mac);
	}

	/**
	 * Calcola il percorso minimo in situazione di emergenza
	 * 
	 * @param mappa relativa al piano in cui si vuole raggiungere l uscita di emergenza
	 * @param posUtente relativa alla posizione attuale dell utente
	 * @return percorso minimo calcolato
	 */
	public ArrayList<Arco> calcolaPercorsoEmergenza(Mappa mappa, Nodo posUtente) {

        ArrayList<Nodo> uscite = mappa.getNodiUscita();
        ArrayList<Nodo> nodi = mappa.getNodi();
        ArrayList<Arco> archiGrafo = mappa.getArchi();
        ArrayList<Arco> archi = new ArrayList<>();

        // considera solo gli archi non incidenti a nodi sotto incendio
        for (Arco arco : archiGrafo)
            if(!arco.getNodoPartenza().isTipoIncendio() && !arco.getNodoArrivo().isTipoIncendio())
                archi.add(arco);
        Nodo migliorUscita = null;

        // algoritmo di Dijkstra: inizializza valori
        Map<Nodo, Integer> costi = new HashMap<>();
        Map<Nodo, Nodo> nodoPrev = new HashMap<>();
        Map<Nodo, Arco> arcoPrev = new HashMap<>();
        ArrayList<Nodo> nodiLocali = new ArrayList<>();

        // copia i nodi della mappa nella lista di nodi locali e setta la distanza a -1 (non ancora calcolata)
        for (Nodo nodo : nodi) {
            nodiLocali.add(nodo);
            costi.put(nodo, -1);
        }

        // il costo per andare dal nodo sorgente (posizione utente) e se stesso è zero per definizione
        costi.put(posUtente, 0);


        while (nodiLocali.size() > 0) {
            // restituisci e rimuovi il nodo con minor costo
            Nodo migliorNodo = getMigliorNodo(nodiLocali, costi);

            nodiLocali.remove(migliorNodo);

            // calcoliamo il costo per tutti i nodi adiacenti
            for (Arco arcoVicino : migliorNodo.getStella(archi)) {
                Nodo nodoVicino = null;
                if (arcoVicino.getNodoArrivo().equals(migliorNodo))
                    nodoVicino = arcoVicino.getNodoPartenza();
                else
                    nodoVicino = arcoVicino.getNodoArrivo();
                if (nodiLocali.contains(nodoVicino)) {
                    int costo = costi.get(migliorNodo) + arcoVicino.getCosto();
                    int costoVicino = costi.get(nodoVicino);

                    if (costoVicino == -1 || costo < costoVicino) {
                        costi.put(nodoVicino, costo);
                        nodoPrev.put(nodoVicino, migliorNodo);
                        arcoPrev.put(nodoVicino, arcoVicino);
                    }
                }
            }

            if (uscite.contains(migliorNodo) && ((migliorUscita == null || costi.get(migliorNodo) < costi.get(migliorUscita))))
                migliorUscita = migliorNodo;
        }

        ArrayList<Arco> percorso = new ArrayList<>();
        try {
            Nodo nodoProssimo = migliorUscita;
            while (nodoPrev.containsKey(nodoProssimo)) {
                percorso.add(arcoPrev.get(nodoProssimo));
                nodoProssimo = nodoPrev.get(nodoProssimo);
            }
        } catch (Exception e) {}

        return percorso;
    }
	
	/**
	 * Calcola il percorso minimo in modalità normale
	 * 
	 * @param mappa relativa al piano in cui si è scelta la destinazione
	 * @param posUtente  relativa al nodo della posizione attuale dell utente
	 * @param destinazione nodo selezionato dall utente 
	 * @return
	 */
	public ArrayList<Arco> calcolaPercorsoNormale(Mappa mappa, Nodo posUtente, Nodo destinazione) {

		ArrayList<Nodo> nodi = mappa.getNodi();

		ArrayList<Arco> archi = mappa.getArchi();
		Nodo migliorUscita = null;

		// algoritmo di Dijkstra: inizializza valori
		Map<Nodo, Integer> costi = new HashMap<>();
		Map<Nodo, Nodo> nodoPrev = new HashMap<>();
		Map<Nodo, Arco> arcoPrev = new HashMap<>();
		ArrayList<Nodo> nodiLocali = new ArrayList<>();

		// copia i nodi della mappa nella lista di nodi locali e setta la distanza a -1 (non ancora calcolata)
		for (Nodo nodo : nodi) {
			nodiLocali.add(nodo);
			costi.put(nodo, -1);
		}

		// il costo per andare dal nodo sorgente (posizione utente) e se stesso è zero per definizione
		costi.put(posUtente, 0);


		while (nodiLocali.size() > 0) {
			// restituisci e rimuovi il nodo con minor costo
			Nodo migliorNodo = getMigliorNodo(nodiLocali, costi);

			nodiLocali.remove(migliorNodo);

			// calcoliamo il costo per tutti i nodi adiacenti
			for (Arco arcoVicino : migliorNodo.getStella(archi)) {
				Nodo nodoVicino = null;
				if (arcoVicino.getNodoArrivo().equals(migliorNodo))
					nodoVicino = arcoVicino.getNodoPartenza();
				else
					nodoVicino = arcoVicino.getNodoArrivo();
				if (nodiLocali.contains(nodoVicino)) {
					int costo = costi.get(migliorNodo) + arcoVicino.getCosto();
					int costoVicino = costi.get(nodoVicino);

					if (costoVicino == -1 || costo < costoVicino) {
						costi.put(nodoVicino, costo);
						nodoPrev.put(nodoVicino, migliorNodo);
						arcoPrev.put(nodoVicino, arcoVicino);
					}
				}
			}

			if (destinazione.equals(migliorNodo) && ((migliorUscita == null || costi.get(migliorNodo) < costi.get(migliorUscita))))
				migliorUscita = migliorNodo;
		}

		ArrayList<Arco> percorso = new ArrayList<>();
		try {
			Nodo nodoProssimo = migliorUscita;
			while (nodoPrev.containsKey(nodoProssimo)) {
				percorso.add(arcoPrev.get(nodoProssimo));
				nodoProssimo = nodoPrev.get(nodoProssimo);
			}
		} catch (Exception e) {}

		return percorso;		
	}

	private Nodo getMigliorNodo(ArrayList<Nodo> nodiLocali, Map<Nodo, Integer> costi){
		
		int costo = -1;
		Nodo migliorNodo = null;

		for(Nodo nodo : nodiLocali){
			int costoNodo = costi.get(nodo);
			if((costo == -1) || (costoNodo != -1 && costoNodo < costo)){
				migliorNodo = nodo;
				costo = costoNodo;
			}
		}
		
		return migliorNodo;
	}
}

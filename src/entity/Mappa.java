package entity;

import java.util.ArrayList;

import utils.Parametri;

public class Mappa {

	private int Piano;
	private String Piantina;           //è un'immagine, è intero perché fa riferimento al codice del drawable associato
	private ArrayList<Nodo> Nodi;   //TODO eliminare, avremo un getNodi() che recupera l'ArrayList dei nodi dagli archi
	private ArrayList<Arco> Archi;

	public Mappa(int piano, String piantina, ArrayList<Nodo> nodi, ArrayList<Arco> archi ) {

		Piano = piano;
		Piantina = piantina;
		Nodi = nodi; 
		Archi = archi;
	}


	public ArrayList<Arco> getArchi() {
		return Archi;
	}
	
	public void setArchi(ArrayList<Arco> archi) {
		Archi = archi;
	}
	
	public int getPiano() {
		return Piano;
	}
	
	public void setPiano(int piano) {
		Piano = piano;
	}
	
	public String getPiantina() {
		return Piantina;
	}
	
	public void setPiantina(String piantina) {
		Piantina = piantina;
	}
	
	public ArrayList<Nodo> getNodi() {
		return Nodi;
	}
	
	public void setNodi(ArrayList<Nodo> nodi) {
		Nodi = nodi;
	}
	
	public ArrayList<Nodo> getNodiUscita() {
		ArrayList<Nodo> uscite = new ArrayList<>();
		for(Nodo nodo : this.Nodi)
			if(nodo.isTipoUscita())
				uscite.add(nodo);
		return uscite;
	}
}

package entity;

import java.util.ArrayList;

public class Mappa {
	
	    private int Piano;
	    private String Piantina;           //è un'immagine, è intero perché fa riferimento al codice del drawable associato
	    private ArrayList<Nodo> Nodi;   //TODO eliminare, avremo un getNodi() che recupera l'ArrayList dei nodi dagli archi
	    
	    
	    public Mappa(int piano, String piantina, ArrayList<Nodo> nodi) {

	        Piano = piano;
	        Piantina = piantina;
	        Nodi = nodi;    

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
}

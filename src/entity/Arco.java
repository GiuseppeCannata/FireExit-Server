package entity;

import java.util.ArrayList;

/*
 * Un arco è formato da un id, da un nodo di partenza e arrivo, da una lista di pesi e 
 * dal piano relativo alla mappa a cui è associato
 */
public class Arco {

	private int id;
	private Nodo nodoPartenza;
	private Nodo nodoArrivo;
	private ArrayList<PesoArco> pesi;
	private int mappaId;

	public Arco(int id, Nodo nodoPartenza, Nodo nodoArrivo, ArrayList<PesoArco> pesi) {

		this.id = id;
		this.nodoPartenza = nodoPartenza;
		this.nodoArrivo = nodoArrivo;
		this.pesi = pesi;
		this.mappaId = nodoPartenza.getmappaId();

	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public Nodo getNodoPartenza() {
		return nodoPartenza;
	}
	
	public void setNodoPartenza(Nodo nodoPartenza) {
		this.nodoPartenza = nodoPartenza;
	}

	public Nodo getNodoArrivo() {
		return nodoArrivo;
	}
	
	public void setNodoArrivo(Nodo nodoArrivo) {
		this.nodoArrivo = nodoArrivo;
	}

	public ArrayList<PesoArco> getPesi() {
		return pesi;
	}
	
	public void setPesi(ArrayList<PesoArco> pesi) {
		this.pesi = pesi;
	}
	
	/*
	 * Permette di calcolare il costo relativo all arco considerando tutti i pesi ad esso associati
	 */
	public int getCosto(){
		int costo = 0;
		for (PesoArco pesoArco : this.pesi)
			costo += pesoArco.getPeso().getPeso() * pesoArco.getValore();
		return costo;
	}
	
	public int getMappaId() {
		return mappaId;
	}
	
	public void setMappaId(int mappaId){
		this.mappaId = mappaId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Arco arco = (Arco) o;

		return id == arco.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
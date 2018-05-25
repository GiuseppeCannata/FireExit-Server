package entity;

import java.util.ArrayList;

/*
 * Un nodo è formato da un id, dal suo MACAdress, da posizioni x e y sulla piantina.
 * Inoltre e caratterizzato dal suo tipo: Incendio o Base
 */
public class Nodo {

	private int Id;
	private String BeaconId;
	private int mappaId;
	private int X, Y;       // valori da 0 a 100 che indicano le coordinate relative alla mappa
	private boolean tipoUscita;
	private boolean tipoIncendio;

	private boolean cambiato;  


	public Nodo(int id, String beaconId, int x, int y, int mappaId) {
		this(id, beaconId, x, y, false, false, mappaId);
	}

	public Nodo(int id, String beaconId, int x, int y, boolean tipoUscita, int mappaId) {
		this(id, beaconId, x, y, tipoUscita, false, mappaId);
	}

	public Nodo(int id, String beaconId, int x, int y, boolean tipoUscita, boolean tipoIncendio, int mappaId) {

		this.Id = id;
		this.BeaconId = beaconId;
		this.X = x;
		this.Y = y;
		this.tipoUscita = tipoUscita;
		this.tipoIncendio = tipoIncendio;
		this.mappaId = mappaId;

	}

	public boolean isTipoUscita() {
		return tipoUscita;
	}

	public void setTipoUscita(boolean tipoUscita) {
		this.tipoUscita = tipoUscita;
	}

	public boolean isTipoIncendio() {
		return tipoIncendio;
	}

	public void setTipoIncendio(boolean tipoIncendio) {
		this.tipoIncendio = tipoIncendio;
	}

	public String getBeaconId() {
		return BeaconId;
	}

	public void setBeaconId(String beaconId) {
		BeaconId = beaconId;
	}

	public int getmappaId() {
		return mappaId;
	}

	public void setmappId(int piano) {
		mappaId = piano;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public boolean isCambiato() {
		return cambiato;
	}

	/*
	 * Pemette di ottenere la stella (archi associati al nodo)
	 * 
	 * @param archi lista degli archi in cui bisogna cercare 
	 */
	public ArrayList<Arco> getStella(ArrayList<Arco> archi) {
		ArrayList<Arco> stella = new ArrayList<>();
		for(Arco arco : archi)
			if (arco.getNodoArrivo().equals(this) || arco.getNodoPartenza().equals(this))
				stella.add(arco);
		return stella;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Nodo nodo = (Nodo) o;

		return Id == nodo.getId();
	}

	@Override
	public int hashCode() {
		return Id;
	}
}

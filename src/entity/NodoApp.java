package entity;

/**
 * Classe che mappa la classe nodo utilizzato lato App.
 * Questa verrà utilizzata dalla libreria Gson per estrarre le informazioni inviate dalla App
 * 
 */
public class NodoApp {

	private int id;
    private String beaconId;
    private int x, y;       // valori da 0 a 100 che indicano le coordinate relative alla mappa
    private boolean tipoUscita;
    private boolean tipoIncendio;
    private int mappaId;
    
    private boolean cambiato;
    
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBeaconId() {
		return beaconId;
	}
	
	public void setBeaconId(String beaconId) {
		this.beaconId = beaconId;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
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
	
	public int getMappaId() {
		return mappaId;
	}
	
	public void setMappaId(int mappaId) {
		this.mappaId = mappaId;
	} 
	
	public boolean isCambiato() {
        return cambiato;
    }
}

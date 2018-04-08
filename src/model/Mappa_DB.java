package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Mappa_DB è una specializzazione della classe Model.
 * presenta le varie query che si possono eseguire sulla tabella Mappa
 * 
 */

public class Mappa_DB extends Model{
	
	public static final String TBL_NAME="MAPPA";
    public static final String FIELD_PIANO="piano";
    public static final String FIELD_PIANTINA="piantina";    
    
    public Mappa_DB() {
    	
    	super();  
    	
    }
    
    
    /*
     * Ottiene il nome della piantina salvata nella cartella images in base al piano.
     * 
     * @param piano
     * @return nome piantina
     */
    public String FindPiantinaByPIANO(int piano) {
    	
    	String nomePiantina = null;
    	
    	try {
 
	    	String query = "select * from "+TBL_NAME+" where "+FIELD_PIANO+" = "+piano;
	    	
	    	OpenConnessione();
		    ResultSet rs = this.selectQuery(query);
		    
		    if(rs.next()) {
		       nomePiantina = rs.getString("piantina");
		    }
		    
		    CloseConnessione();
		    st.close();
		    	
    	} catch (SQLException e) {
			e.printStackTrace();
		} 
			
			return nomePiantina;	
    }
    
    public ArrayList<Integer> getPiani() {
    	
         ArrayList<Integer> piani = new ArrayList<Integer>();
    	
    	try {
 
	    	String query = "select * from "+TBL_NAME;
	    	
	    	OpenConnessione();
		    ResultSet rs = this.selectQuery(query);
		    
		    while(rs.next()) {
		       piani.add(rs.getInt("piano"));
		    }
		    
		    CloseConnessione();
		    st.close();
		    	
    	} catch (SQLException e) {
			e.printStackTrace();
		} 
			
			return piani;	
    }
    
   public void method(){
    	
	   //VUOTO
    	
    }

}

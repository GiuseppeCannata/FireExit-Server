package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import entity.PesoArco;

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
    
    public ArrayList<Mappa> getListInfoMappe(){
    	
        ArrayList<Mappa> mappe = new ArrayList<Mappa>();
     	Mappa mappa;
     	
     	try {
         	
 	    	String query = "select * from "+TBL_NAME;
 	    	System.out.println(query);
 	    	
 	    	OpenConnessione();
 		    ResultSet rs = selectQuery(query);
 		    
 		    while(rs.next()) {
 					    	
 		    	mappa = new Mappa(
 		    			          rs.getInt(this.FIELD_PIANO),
 		    			          rs.getString(this.FIELD_PIANTINA),
 		    			          null,
 		    			          null
 		    			          ); 
 		    	
 		    	mappe.add(mappa);
 		    }
 		    
 		    CloseConnessione();
 		    st.close();
 			
     	} catch (SQLException e) {
 			e.printStackTrace();	
 		}
     	
     	return mappe;
    }
    
    public boolean InserimentoInfo(int piano, String piantina) throws SQLException {
    	
    	boolean esito = false;
    	
    	String query ="insert into "+TBL_NAME+" ( "+FIELD_PIANO+","
    			                                   +FIELD_PIANTINA+
    			                              " )"+
    			                     " values( "+piano+","
    	                                       +"'"+piantina+"' )";
    	                                       
    			       
    	System.out.println(query);
    	
		OpenConnessione();
		esito = updateQueryCostrain(query);
	    
	    CloseConnessione();
	    st.close();
	
	    return esito;
    	
    }
    
    
    public boolean delete(int piano){
   	 
    	boolean esito = false;
    	
      	 try {
           	
   	    	String query = "delete from "+TBL_NAME+" where "+this.FIELD_PIANO+"="+piano;
   	    	
   	    	OpenConnessione();
   		    this.updateQuery(query);
   		    
   		    CloseConnessione();
   		    st.close();
   		    
   		    esito = true;
   		    
       	} catch (SQLException e) {
   			e.printStackTrace();	
   		}
      	 
      	return esito;
   	}
    
   public void method(){
    	
	   //VUOTO
    	
    }

}

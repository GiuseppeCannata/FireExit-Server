package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Nodo;
import entity.PesoArco;

public class PesoArco_DB extends Model{
	
	public static final String TBL_NAME = "PesoArco";
    public static final String FIELD_ID = "id";
    public static final String FIELD_IDARCO = "idArco"; 
    public static final String FIELD_IDPESO = "idPeso"; 
    public static final String FIELD_VALORE = "valore"; 
    
    
    public PesoArco_DB() {
    	
    	super();  
    	
    }
    
    public ArrayList<PesoArco> findPesiById(int Id){
    	
    	ArrayList<PesoArco> pesi = new ArrayList<PesoArco>();
    	Peso_DB pdb = new Peso_DB();
    	
    	try {
 
	    	String query = "select * from "+TBL_NAME+" where "+FIELD_IDARCO+" = "+Id;
	    	System.out.println(query);
	    	
	    	OpenConnessione();
		    ResultSet rs = this.selectQuery(query);
		    
		    while(rs.next()) {
		    	
		    	int id = rs.getInt(FIELD_ID);
		        int idArco = rs.getInt(FIELD_IDARCO);
		        int idPeso = rs.getInt(FIELD_IDPESO);
		        int valore = rs.getInt(FIELD_VALORE);
		        
		        pesi.add( new PesoArco(id , idArco , pdb.findById(idPeso) , valore));
	 			  
	 		}
		    
		    CloseConnessione();
		    st.close();
		    	
    	} catch (SQLException e) {
			e.printStackTrace();
		} 
			
			return pesi;
    }
   
    
   public void method(){
    	
	   //VUOTO
    	
    }
}
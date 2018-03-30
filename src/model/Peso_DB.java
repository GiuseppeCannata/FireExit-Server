package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Nodo;
import entity.Peso;

public class Peso_DB  extends Model{
	
	public static final String TBL_NAME = "PESO";
    public static final String FIELD_ID = "id";
    public static final String FIELD_DESCRIZIONE = "descrizione";    
    public static final String FIELD_PESO = "peso";  
    
    public Peso_DB() {
    	
    	super();  
    	
    }
    
    public Peso findById(int Id) {
    	
     Peso peso = null;
    	
        try {
         	
 	    	String query = "select * from "+TBL_NAME+" where "+FIELD_ID+" = "+Id;
 	    	System.out.println(query);
 	    	
 	    	OpenConnessione();
 		    ResultSet rs = selectQuery(query);
 		    
 		    if(rs.next()) {
 		    
 			    peso = new Peso(
 			    		             rs.getInt(FIELD_ID),
 			    		             rs.getString(FIELD_DESCRIZIONE),
 			    		             rs.getInt(FIELD_PESO)
		    		             );
 			    
 			    System.out.println(peso.toString());
		  
 		    }
 		    
 		    CloseConnessione();
 		    st.close();
 			
     	} catch (SQLException e) {
 			e.printStackTrace();	
 		}
    	 
    	 return peso;
    }
    
     
   public void method(){
    	
	   //VUOTO
    	
    }
   
}

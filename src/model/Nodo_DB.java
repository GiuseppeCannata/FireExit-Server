package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Nodo;

public class Nodo_DB extends Model{
	
	public static final String TBL_NAME="NODO";
    public static final String FIELD_IDBEACON="ID_beacon";
    public static final String FIELD_PIANO="piano";
    public static final String FIELD_X="x";  
    public static final String FIELD_Y="y";  
    public static final String FIELD_TIPO="tipo"; 
    
    public Nodo_DB() {
    	
    	super();
    	
    }
    
    public void method(){ 		
    	
    	
    }
    
    public int FindPianoByID(String ID) {
    	
    	int piano = 0;
    	
    	try {
    		
	    	String query = "select "+FIELD_PIANO+" from "+TBL_NAME+" where "+FIELD_IDBEACON+" = '"+ID+"'";
	    	System.out.println(query);
	    	
	    	OpenConnessione();
		    ResultSet rs = selectQuery(query);
		    
		    if(rs.next())
		    	piano = rs.getInt("piano");		      
		    
		    CloseConnessione();
		    st.close();
			
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return piano;	
  	
    	
    }
    
    
    public void FindNodiByPiano( ArrayList<Nodo> nodi , int piano) {
    	
    	try {
        	
	    	String query = "select * from "+TBL_NAME+" where "+FIELD_PIANO+" = "+piano;
	    	System.out.println(query);
	    	
	    	OpenConnessione();
		    ResultSet rs = selectQuery(query);
		    
		    while(rs.next()) {
			  
			  Nodo nodo = new Nodo( rs.getString(FIELD_IDBEACON),
					                piano,
					                rs.getInt(FIELD_X),
					                rs.getInt(FIELD_Y), 
					                rs.getInt(FIELD_TIPO)
					               );
			  
			  nodi.add(nodo);
			  
		    }
		    
		    CloseConnessione();
		    st.close();
			
    	} catch (SQLException e) {
			e.printStackTrace();	
		}
    	
     }
    	
    public boolean setTipo(int Tipo, String macAdrs) {
    	
    	boolean esito = false;
    	
    	try {
        	
	    	String query ="update "+TBL_NAME+" set "+FIELD_TIPO+"="+Tipo+" where "+FIELD_IDBEACON+"='"+macAdrs+"'";
	    	System.out.println(query);
	    	
	    	OpenConnessione();
	    	esito = updateQuery(query);
		    
		    CloseConnessione();
		    st.close();
			
    	} catch (SQLException e) {
			e.printStackTrace();	
		}	
    	
    	return esito;
    	
    }

}

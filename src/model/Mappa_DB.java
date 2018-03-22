package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mappa_DB extends Model{
	
	private PreparedStatement st;
	
	public static final String TBL_NAME="MAPPA";
    public static final String FIELD_PIANO="piano";
    public static final String FIELD_PIANTINA="piantina";    
    
    public Mappa_DB() {
    	
    	super();
    	
    }
    
    public void method(){
    	
    	
    }
    
public String FindPiantinaByPIANO(int piano) {
    	
    	ResultSet rs = null;
    	String nomePiantina = null;
    	
    	try {
 
	    	String query = "select * from "+TBL_NAME+" where "+FIELD_PIANO+" = "+piano;
	    	
	    	OpenConnessione();
		    st = conn.prepareStatement(query);
		    rs = st.executeQuery();
		    
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

}

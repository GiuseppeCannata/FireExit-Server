package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

/*
 * Classe padre di tutte le classi presenti nel package Model.
 * Fornisce i metodi per:
 * 
 * 1) l apertura/chiusura connessione con il DB
 * 2) Eseguire le select
 * 3) Eseguire gli update
 * 
 * E' una classe astratta e come tale presenterà solamente la definifione del metodo method
 * il cui corpo, anche se vuoto, verrà definito nelle specializzazioni di queta.
 * 
 */

public abstract class Model {
	
	private Connection conn; 
	private String USER;
	private String PASS;
	protected Statement st;
	private ResultSet rs;
	
	
	public Model() {
		
		conn = null;
		USER = "user";
		PASS = "0000";
		
		
	}
	
	public void OpenConnessione() throws SQLException {
		
		conn = DriverManager.getConnection("jdbc:derby://localhost:1527/FireExit;create=true", USER, PASS); 
		System.out.println("Conessione stabilita");
		
		
	}
	
    public void CloseConnessione() throws SQLException {
    	
		
		conn.close();
		System.out.println("Chiusura COnnesione");
		
		
	}
    
    /**
     * esegue una query sql per la selezione di dati
     *
     * @param sql stringa contenente la query in sql
     * @return risultato della query
     */
    protected ResultSet selectQuery(String sql) {

        try {
        	
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            
        } catch (SQLException se) {
            se.printStackTrace();
        }
        
        return rs;
    }
    
    /**
     * esegue una query sql per l'update dei dati (inserimento, modifica e eliminazione)
     *
     * @param sql stringa sql
     * @return true , avvenuta con successo
     */
    protected boolean updateQuery(String sql) {

        boolean result = false;
        
        try {
        	
            st = conn.createStatement();
            st.executeUpdate(sql);
            result = true;
            
        } catch (SQLException se) {
            se.printStackTrace();
        } 

        return result;
    }
    
    protected boolean updateQueryCostrain(String sql) throws SQLException{

        boolean result = false;
        	
        st = conn.createStatement();
        st.executeUpdate(sql);
        result = true;

        return result;
    }
    
    
    
    
    public abstract void method();

}

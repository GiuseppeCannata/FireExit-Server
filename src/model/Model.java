package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public abstract class Model {
	
	Connection conn; 
	String USER;
	String PASS;
	
	
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
    
    public abstract void method();

}

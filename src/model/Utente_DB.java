package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Arco;
import entity.Nodo;
import entity.PesoArco;

public class Utente_DB extends Model {
	
	private static final String TBL_NAME = "Utente";
	private static final String TOKEN = "TOKEN";
	
	public Utente_DB() {
		
	}
	
	public boolean insert(String token){

		boolean esito = false;
		
		String query ="insert into "+TBL_NAME+" ( "+TOKEN+" )"+
				" values( '"+token+"' )";

		System.out.println(query);

		try {
			
			OpenConnessione();
			updateQueryCostrain(query);
			CloseConnessione();
			st.close();
			esito = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return esito;
	}
	
	public ArrayList<String> getListToken(){

		ArrayList<String> Tokens = new ArrayList<String>();

		try {

			String query = "select * from "+TBL_NAME;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			while(rs.next()) {

				String token = rs.getString(TOKEN);
				Tokens.add(token);
			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return Tokens;
	}
	
	public void method() {
		
	}

}

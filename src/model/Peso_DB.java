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

	public int findByDescrizione(String Descrizione) {

		int row = 0;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_DESCRIZIONE+" = '"+Descrizione+"'";
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			if(rs.next())
				row = rs.getInt(FIELD_ID);

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return row;
	}

	public ArrayList<Peso> getListPesi() {

		Peso peso = null;
		ArrayList<Peso> pesi = new ArrayList<Peso>();

		try {

			String query = "select * from "+TBL_NAME;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			while(rs.next()) {

				peso = new Peso(
						rs.getInt(FIELD_ID),
						rs.getString(FIELD_DESCRIZIONE),
						rs.getInt(FIELD_PESO)
						);
				pesi.add(peso);
			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return pesi;
	}

	public boolean updatePeso(Peso peso) {

		boolean esito = false;
		
		try {

			String query ="update "+TBL_NAME+" set "
					+FIELD_PESO+" = "+peso.getPeso()+""
					+"where "+FIELD_ID+"= "+peso.getId()+"";
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

	public boolean delete(int Id) {

		boolean esito = false;

		String query = "DELETE FROM "+TBL_NAME+" where "+FIELD_ID+" = "+Id;

		System.out.println(query);

		try {
			OpenConnessione();
			
			if(updateQuery(query))
				esito = true;

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return esito;
	}

	public boolean inserimentoPeso(Peso peso) throws SQLException{

		boolean esito = false;

		String query ="insert into "+TBL_NAME+" ( "+FIELD_DESCRIZIONE+","
				+FIELD_PESO+
				" )"+
				" values( '"+peso.getDescrizione()+"',"
				+peso.getPeso()+
				" )";	       
		System.out.println(query);

		OpenConnessione();
		esito = updateQueryCostrain(query);

		CloseConnessione();
		st.close();

		return esito;		

	}


	public void method(){

		//VUOTO

	}

}

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Mappa;

/*
 * Mappa_DB è una specializzazione della classe Model.
 * presenta le varie query che si possono eseguire sulla tabella Mappa
 * 
 */

public class Mappa_DB extends Model{

	private static final String TBL_NAME = "MAPPA";
	private static final String FIELD_PIANO = "PIANO";
	private static final String FIELD_PIANTINA = "PIANTINA";  
	private static final String FIELD_STATOEMERGENZA = "STATOEMERGENZA"; 

	public Mappa_DB() {

		super();  

	}
	
	public boolean controllaStatoEmergenza() {

		boolean esito = false;
		
		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_STATOEMERGENZA+" = 1 " ;

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			if(rs.next()) 
				esito = true;
			

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 

     	return esito;	
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
			ResultSet rs = selectQuery(query);

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
			ResultSet rs = selectQuery(query);

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
	
	public int getStatoEmergenzaByPiano(int piano){
		
		int stato = 0;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_PIANO+" = "+piano;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			if(rs.next())
				stato = rs.getInt(FIELD_STATOEMERGENZA);

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return stato;
	}

	public boolean InserimentoInfo(int piano, String piantina) throws SQLException {

		boolean esito = false;

		String query ="insert into "+TBL_NAME+" ( "+FIELD_PIANO+","
				                                   +FIELD_PIANTINA+" )"+
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

			String query = "delete from "+TBL_NAME+" where "+FIELD_PIANO+"="+piano;

			OpenConnessione();
			updateQuery(query);

			CloseConnessione();
			st.close();

			esito = true;

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return esito;
	}
	
	public boolean updateStatoEmergenza(int stato,int piano) {

		boolean esito = false;
		
		String query ="update "+TBL_NAME+" set "
				+FIELD_STATOEMERGENZA+" = "+stato+" "
				+"where "+FIELD_PIANO+"= "+piano+"";
		System.out.println(query);

		try {
			
			OpenConnessione();
			esito = updateQuery(query);

			CloseConnessione();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return esito;	

	} 

	public void method(){

		//VUOTO

	}

}

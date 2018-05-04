package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Arco;
import entity.Nodo;
import entity.PesoArco;

public class Arco_DB extends Model{

	private static final String TBL_NAME = "ARCO";
	private static final String FIELD_ID = "ID";
	private static final String FIELD_NODOPARTENZA = "NODOPARTENZAID";
	private static final String FIELD_NODOARRIVO = "NODOARRIVOID";   
	private static final String FIELD_MAPPAID = "MAPPAID"; 

	public Arco_DB() {

		super();  

	}

	public ArrayList<Arco> findArchiByPiano(int mappaId){

		ArrayList<Arco> Archi = new ArrayList<Arco>();

		Nodo_DB ndb = new Nodo_DB();
		PesoArco_DB padb = new PesoArco_DB();

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_MAPPAID+" = "+mappaId;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			while(rs.next()) {

				Nodo nodoPartenza = ndb.FindNodoById(rs.getInt(FIELD_NODOPARTENZA));
				Nodo nodoArrivo = ndb.FindNodoById(rs.getInt(FIELD_NODOARRIVO));
				ArrayList<PesoArco> pesi = padb.findPesiByIdArco(rs.getInt(FIELD_ID));

				Arco arco = new Arco( rs.getInt(FIELD_ID),
						nodoPartenza,
						nodoArrivo,
						pesi
						);

				Archi.add(arco);

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return Archi;
	}

	public ArrayList<Arco> getListArchi(){

		ArrayList<Arco> Archi = new ArrayList<Arco>();

		Nodo_DB ndb = new Nodo_DB();
		PesoArco_DB padb = new PesoArco_DB();

		try {

			String query = "select * from "+TBL_NAME;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			while(rs.next()) {

				Nodo nodoPartenza = ndb.FindNodoById(rs.getInt(FIELD_NODOPARTENZA));
				Nodo nodoArrivo = ndb.FindNodoById(rs.getInt(FIELD_NODOARRIVO));
				ArrayList<PesoArco> pesi = padb.findPesiByIdArco(rs.getInt(FIELD_ID));

				Arco arco = new Arco( rs.getInt(FIELD_ID),
						nodoPartenza,
						nodoArrivo,
						pesi
						);

				Archi.add(arco);

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return Archi;
	}

	public ArrayList<Arco> getArchiByNodoId(int Id){

		ArrayList<Arco> Archi = new ArrayList<Arco>();

		Nodo_DB ndb = new Nodo_DB();
		PesoArco_DB padb = new PesoArco_DB();

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_NODOPARTENZA+"="+Id+" or "+FIELD_NODOARRIVO+" = "+Id;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			while(rs.next()) {

				Nodo nodoPartenza = ndb.FindNodoById(rs.getInt(FIELD_NODOPARTENZA));
				Nodo nodoArrivo = ndb.FindNodoById(rs.getInt(FIELD_NODOARRIVO));
				ArrayList<PesoArco> pesi = padb.findPesiByIdArco(rs.getInt(FIELD_ID));

				Arco arco = new Arco( rs.getInt(FIELD_ID),
						nodoPartenza,
						nodoArrivo,
						pesi
						);

				Archi.add(arco);

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return Archi;

	}

	public void deleteArco(Arco arco){

		PesoArco_DB padb = new PesoArco_DB();

		try {

			String query = "delete from "+TBL_NAME+" where "+FIELD_ID+"="+arco.getId();

			OpenConnessione();
			updateQuery(query);

			CloseConnessione();
			st.close();

			//eliminazione dei pesoArco ASSOCIATI ALL ARCO
			for(PesoArco pa: arco.getPesi())
				padb.delete(pa.getId());

		} catch (SQLException e) {
			e.printStackTrace();	
		}

	}

	public boolean controlloEsistenzaArco(int IDPartenza, int IDArrivo) {

		boolean esito = false;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_NODOPARTENZA+"="+IDPartenza+" and "+FIELD_NODOARRIVO+" = "+IDArrivo;
			System.out.println(query);

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

	public void insertArco(int IDPartenza, int IDArrivo, int piano) {

		String query ="insert into "+TBL_NAME+" ( "+FIELD_NODOPARTENZA+","
				+FIELD_NODOARRIVO+","
				+FIELD_MAPPAID+
				" )"+
				" values( "+IDPartenza+","
				+IDArrivo+","
				+piano+
				" )";
		System.out.println(query);

		try {
			OpenConnessione();

			updateQuery(query);

			CloseConnessione();
			st.close(); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int findIDArcoByNodoPIDNodoAID(int IDPartenza, int IDArrivo) {

		int esito = 0;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_NODOPARTENZA+"="+IDPartenza+" and "+FIELD_NODOARRIVO+" = "+IDArrivo;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			if(rs.next()) 
				esito = rs.getInt(FIELD_ID);

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return esito;

	}

	public void method(){

		//VUOTO

	}
}

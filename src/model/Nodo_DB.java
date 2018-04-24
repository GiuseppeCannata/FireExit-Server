package model;

import java.sql.*;
import java.util.ArrayList;

import entity.Nodo;

/*
 * Nodo_DB è una specializzazione della classe Model.
 * presenta le varie query che si possono eseguire sulla tabella Nodo
 * 
 */
public class Nodo_DB extends Model{

	public static final String TBL_NAME = "NODO";
	public static final String FIELD_ID = "ID";
	public static final String FIELD_IDBEACON = "BEACONID";
	public static final String FIELD_PIANO = "piano";
	public static final String FIELD_X = "x";  
	public static final String FIELD_Y = "y";  
	public static final String FIELD_TIPO = "tipo"; 

	public Nodo_DB() {

		super();

	}

	/*
	 * Ottiene il piano dei vari Beacon in base al loro macAdrs
	 * 
	 * @param macAdrs
	 * @return piano
	 */
	public int findPianoByMAC(String MacAdrs) {

		int piano = 0;

		try {

			String query = "select "+FIELD_PIANO+" from "+TBL_NAME+" where "+FIELD_IDBEACON+" = '"+MacAdrs+"'";
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

	/*
	 * Ottiene i nodi in base al piano 
	 * 
	 * @param nodi , piano
	 */ 
	public void findNodiByPiano( ArrayList<Nodo> nodi , int piano) {

		boolean tipoIncendio;
		boolean tipoUscita;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_PIANO+" = "+piano;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			while(rs.next()) {

				Nodo nodo;

				switch (rs.getInt(FIELD_TIPO)) {

				case 1:
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON),
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y),  
							rs.getInt(FIELD_PIANO));
					nodi.add(nodo);
					break;

				case 2:
					tipoUscita = false;
					tipoIncendio = true;
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					nodi.add(nodo);
					break;

				case 3:
					tipoUscita = true;
					tipoIncendio = false;
					nodo  = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					nodi.add(nodo);
					break;
				}

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

	}

	/*
	 * Esegue un update del tipo dei nodi
	 * 
	 * @param tipo , macAdrs
	 * @return true/false
	 */
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




	public Nodo FindNodoById(int id) {

		Nodo nodo = null;
		boolean tipoIncendio;
		boolean tipoUscita;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_ID+" = "+id;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			if(rs.next()) {


				switch (rs.getInt(FIELD_TIPO)) {

				case 1:
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON),
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y),  
							rs.getInt(FIELD_PIANO));
					break;

				case 2:
					tipoUscita = false;
					tipoIncendio = true;
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					break;

				case 3:
					tipoUscita = true;
					tipoIncendio = false;
					nodo  = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					break;
				}

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return nodo;

	}

	public Nodo FindNodoByMac(String macAdrs) {

		Nodo nodo = null;
		boolean tipoIncendio;
		boolean tipoUscita;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_IDBEACON+" = '"+macAdrs+"'";
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			if(rs.next()) {

				switch (rs.getInt(FIELD_TIPO)) {

				case 1:
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON),
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y),  
							rs.getInt(FIELD_PIANO));
					break;

				case 2:
					tipoUscita = false;
					tipoIncendio = true;
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					break;

				case 3:
					tipoUscita = true;
					tipoIncendio = false;
					nodo  = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					break;
				}

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return nodo;

	}
	
	public boolean FindNodiSottoIncendioByPiano(int piano) {

		boolean esito = false;
		
		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_TIPO+" = 1 and "+FIELD_PIANO+" = "+piano;
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

	public ArrayList<Nodo> getListNodi(){

		boolean tipoIncendio;
		boolean tipoUscita;
		ArrayList<Nodo> nodi = new ArrayList<Nodo>();

		try {

			String query = "select * from "+TBL_NAME;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = selectQuery(query);

			while(rs.next()) {

				Nodo nodo;

				switch (rs.getInt(FIELD_TIPO)) {

				case 1:
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON),
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y),  
							rs.getInt(FIELD_PIANO));
					nodi.add(nodo);
					break;

				case 2:
					tipoUscita = false;
					tipoIncendio = true;
					nodo = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					nodi.add(nodo);
					break;

				case 3:
					tipoUscita = true;
					tipoIncendio = false;
					nodo  = new Nodo(rs.getInt(FIELD_ID),
							rs.getString(FIELD_IDBEACON), 
							rs.getInt(FIELD_X),
							rs.getInt(FIELD_Y), 
							tipoUscita, 
							tipoIncendio, 
							rs.getInt(FIELD_PIANO)
							);
					nodi.add(nodo);
					break;
				}

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

		return nodi;

	}

	public boolean updateNodo(Nodo nodo) throws SQLException {

		boolean esito = false;
		int tipo;

		if(nodo.isTipoIncendio())
			tipo = 2;
		else if(nodo.isTipoUscita())
			tipo = 3;
		else
			tipo = 1;

		String query ="update "+TBL_NAME+" set "
				+FIELD_IDBEACON+" = '"+nodo.getBeaconId()+"',"
				+FIELD_PIANO+" = "+nodo.getmappaId()+","
				+FIELD_TIPO+" = "+tipo+","
				+FIELD_X+" = "+nodo.getX()+","
				+FIELD_Y+" = "+nodo.getY()+""
				+"where "+FIELD_ID+"= "+nodo.getId()+"";
		System.out.println(query);

		OpenConnessione();
		esito = updateQueryCostrain(query);

		CloseConnessione();
		st.close();

		return esito;	

	}  

	public boolean inserimentoNodo(Nodo nodo) throws SQLException{

		boolean esito = false;
		int tipo;

		if(nodo.isTipoIncendio())
			tipo = 2;
		else if(nodo.isTipoUscita())
			tipo = 3;
		else
			tipo = 1;

		String query ="insert into "+TBL_NAME+" ( "+FIELD_IDBEACON+","
				+FIELD_PIANO+","
				+FIELD_TIPO+","
				+FIELD_X+","
				+FIELD_Y+ " )"+
				" values( '"+nodo.getBeaconId()+"',"
				+nodo.getmappaId()+","
				+tipo+","
				+nodo.getX()+","
				+nodo.getY()+" )";


		System.out.println(query);

		OpenConnessione();
		esito = updateQueryCostrain(query);

		CloseConnessione();
		st.close();

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

	public void method(){ 		

		//VUOTO

	}
}
